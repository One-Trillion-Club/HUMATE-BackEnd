package com.otclub.humate.domain.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otclub.humate.common.entity.Member;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.common.service.SmsService;
import com.otclub.humate.common.upload.S3Uploader;
import com.otclub.humate.domain.auth.dto.*;
import com.otclub.humate.domain.auth.jwt.JwtDTO;
import com.otclub.humate.domain.auth.jwt.JwtGenerator;
import com.otclub.humate.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLDecoder;
import java.time.Duration;
import java.util.Map;
import java.util.Random;

import static com.otclub.humate.domain.auth.util.AuthUtil.*;

/**
 * 인증/인가 서비스 구현체
 * @author 조영욱
 * @since 2024.07.28
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.28  	조영욱        최초 생성
 * 2024.07.30   조영욱        JWT 토큰 리프레시 추가
 * 2024.07.31   조영욱        휴대폰 인증 추가
 * 2024.07.31   조영욱        여권 인증 추가
 * 2024.08.05   조영욱        SMS 연동 추가
 * </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final MemberMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final StringRedisTemplate redisTemplate;
    private final S3Uploader s3Uploader;
    private final SmsService smsService;

    // CODEF 액세스 토큰 발급을 위한 요청 URL
    @Value("${codef.accessTokenIssuanceUrl}")
    private String accessTokenIssuanceUrl;
    // CODEF 액세스 토큰 발급을 위한 Auth Value
    @Value("${codef.accessTokenIssuanceAuthValue}")
    private String accessTokenIssuanceAuthValue;
    // CODEF 여권 인증 URL
    @Value("${codef.passportVerificationUrl}")
    private String passportVerificationUrl;

    /**
     * 새 사용자 회원 가입
     * 로그인 id, 닉네임 중복 시 실패한다.
     *
     * @author 조영욱
     * @param dto 회원가입 사용자 정보
     */
    @Transactional
    @Override
    public int signUp(SignUpRequestDTO dto, MultipartFile image) {
        log.info(dto.toString());

        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        if (dto.getNationality() == 1 && dto.getPhone() == null) {
            // 한국인일 시 phone 인증 성공 코드 검증
            throw new CustomException(ErrorCode.NOT_VALID_INPUT);

        } else if (dto.getNationality() != 1 && dto.getPassportNo() == null) {
            // 외국인일 시 passport 인증 성공 코드 검증
            throw new CustomException(ErrorCode.NOT_VALID_INPUT);
        }

        String verifyRedisKey = "verification:"+ (dto.getNationality() == 1 ? dto.getPhone() : dto.getPassportNo());
        String verificationCode = operations.get(verifyRedisKey);

        if (!dto.getVerifyCode().equals(verificationCode)) {
            // 인증 정보 부적합 시 예외
            throw new CustomException(ErrorCode.VERIFICATION_INVALID);
        } else {
            // 인증 완료 시 레디스 키 삭제
            redisTemplate.delete(verifyRedisKey);
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        if (image != null) {
            dto.setProfileImgUrl(s3Uploader.uploadImage(image));
        }

        int result = 0;
        try {
            result = mapper.insertMember(dto);
        } catch (DuplicateKeyException e) {
            throw new CustomException(ErrorCode.DUPLICATE_KEY);
        }
        return result;
    }

    /**
     * loginId 중복 체크
     *
     * @author 조영욱
     * @param loginId 중복 확인 할 id
     */
    @Override
    public boolean checkAvailableLoginId(String loginId) {
        return mapper.selectMemberByLoginId(loginId) == null;
    }

    /**
     * 회원 로그인
     *
     * @author 조영욱
     * @param dto 아이디, 패스워드
     */
    @Override
    public JwtDTO logIn(LogInRequestDTO dto) {
        Member member = mapper.selectMemberByLoginId(dto.getLoginId());

        if (member == null || !passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.NOT_VALID_USER_INFORMATION);
        }

        try {
            JwtDTO jwtDTO = jwtGenerator.generateToken(member);
            member.setRefreshToken(jwtDTO.refreshToken());
            mapper.updateRefreshToken(member);

            return jwtDTO;
        } catch (Exception e) {
            throw new CustomException(ErrorCode.UNEXPECTED_EXCEPTION);
        }
    }

    /**
     * JWT 토큰 리프레시
     * 액세스 토큰 만료 시 리프레시 토큰을 통한 토큰 리프레시
     *
     * @author 조영욱
     * @param memberId 유저 id
     * @param refreshToken validate 된 리프레시 토큰 값
     */
    @Override
    public JwtDTO refreshJwtToken(String memberId, String refreshToken) throws Exception {
        Member member = mapper.selectMemberById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTS_MEMBER));

//        if (!refreshToken.equals(member.getRefreshToken())) {
//            throw new Exception();
//        }

        try {
            JwtDTO jwtDTO = jwtGenerator.generateToken(member);
            member.setRefreshToken(jwtDTO.refreshToken());
            mapper.updateRefreshToken(member);

            return jwtDTO;
        } catch (Exception e) {
            throw new CustomException(ErrorCode.UNEXPECTED_EXCEPTION);
        }
    }

    /**
     * 휴대폰 인증 번호 생성
     *
     * @author 조영욱
     */
    public String generatePhoneVerificationCode(GeneratePhoneVerificationCodeRequestDTO dto) {
        String phone = dto.getPhone();

        // 이미 가입된 휴대폰 번호일 시 에러
        Member member = mapper.selectMemberByPhone(phone);
        if (member != null) {
            throw new CustomException(ErrorCode.ALREADY_EXISTS_PHONE);
        }

        // 6자리 랜덤 숫자 생성
        Random random = new Random();
        String code = String.format("%06d", random.nextInt(1000000)); // 6자리 문자열로 포맷팅 (앞자리 0 포함)

        // 레디스에 코드 저장 (ttl 설정 포함)
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String redisCodeKey = "code:" + phone;

        // 레디스에 저장되는 구조: { "code:01012341234" : "012345" }
        operations.set(redisCodeKey, code, Duration.ofSeconds(REDIS_PHONE_CODE_KEY_TTL));

        // SMS 메시지 전송
        smsService.sendPhoneVerifySMS(phone, code);

        return code;
    }

    /**
     * 휴대폰 번호 인증
     *
     * @author 조영욱
     * @return 성공 시 인증 성공을 보증하기 위한 key 리턴, 실패 시 null
     */
    @Override
    public String verifyPhone(PhoneVerificationRequestDTO dto) {
        String phone = dto.getPhone();
        String code = dto.getCode();

        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String redisCodeKey = "code:" + phone;

        String cachedCode = operations.get(redisCodeKey);

        // 해당 핸드폰 번호에 대해 코드 없을 시 false 리턴
        if (cachedCode == null) {
            log.info("\n해당 핸드폰 번호에 대해 캐시된 코드 없어서 인증 실패");
            return null;
        }

        if (code.equals(cachedCode)) { // 레디스에 저장된 코드와 같을 시 통과
            redisTemplate.delete(redisCodeKey); // 존재하던 키 삭제

            // 휴대폰 번호 인증 성공을 보증하기 위한 key 생성. 추후 회원가입 요청 시 다시 받아서 validate
            String redisVerificationSuccessKey = "verification:" + phone;
            Random random = new Random();
            String successCode = String.format("%06d", random.nextInt(1000000));
            // 레디스에 저장되는 구조: { "verification:01012341234" : "012345" }
            operations.set(redisVerificationSuccessKey, successCode, Duration.ofSeconds(REDIS_VERIFICATION_SUCCESS_KEY_TTL));
            log.info("\n휴대폰 인증 성공");
            return successCode;
        } else {
            log.info("\n저장된 코드와 달라서 인증 실패");
            return null;
        }
    }

    /**
     * 여권 번호 인증
     *
     * @author 조영욱
     * @return 성공 시 인증 성공을 보증하기 위한 key 리턴, 실패 시 null
     */
    @Override
    public String verifyPassport(PassportVerificationRequestDTO dto) {

        // Redis에서 여권 인증을 위한 액세스 토큰 확인
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String accessToken = operations.get("passport_verification_access_token");

        if (accessToken == null) {
            log.info("\n레디스에 여권인증키 없음, 재발급 시도\n");
            // 새 여권인증키 발급, 레디스에 저장
            accessToken = issuePassportVerificationAccessToken();
        }

        try {
            // 여권 인증 요청
            WebClient webClient = WebClient.builder().build();
            String response = webClient.post()
                    .uri(passportVerificationUrl)
                    .header("Authorization", accessToken)
                    .bodyValue(dto)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // 동기식으로 처리

            String r = URLDecoder.decode(response, "UTF-8");

            log.info("\n\n여권인증 결과\n" + r + "\n");

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> mapResult = mapper.readValue(r, Map.class);

            if (((Map)(mapResult.get("data"))).get("resAuthenticity").equals("1")) {
                log.info("\n여권인증: 여권 정보 정상\n");
                // 여권 번호 인증 성공을 보증하기 위한 key 생성. 추후 회원가입 요청 시 다시 받아서 validate
                String redisVerificationSuccessKey = "verification:" + dto.getPassportNo();
                Random random = new Random();
                String successCode = String.format("%06d", random.nextInt(1000000));
                // 레디스에 저장되는 구조: { "verification:01012341234" : "012345" }
                operations.set(redisVerificationSuccessKey, successCode, Duration.ofSeconds(REDIS_VERIFICATION_SUCCESS_KEY_TTL));

                return successCode;
            } else {
                log.info("\n여권인증: 여권 정보 비정상\n");
                return null;
            }
        } catch (Exception e) {
            log.info("\n여권 인증 에러\n{}", e);
        }

        return null;
    }

    /**
     * 여권 인증을 위한 API 액세스 토큰 발급
     *
     * @author 조영욱
     * @return 액세스 토큰
     */
    private String issuePassportVerificationAccessToken() {
        log.info("\n\n**여권인증 액세스키 발급**\n\n");
        WebClient webClient = WebClient.builder().build();

        // 여권 인증을 위한 액세스 키 발급
        Map<String, Object> response = webClient.post()
                .uri(accessTokenIssuanceUrl)
                .header("Authorization", accessTokenIssuanceAuthValue)
                .retrieve()
                .bodyToMono(Map.class)
                .block(); // 동기식으로 처리

        String accessToken = "Bearer " + response.get("access_token");

        // 발급된 액세스 토큰 레디스에 저장
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("passport_verification_access_token", accessToken, Duration.ofSeconds(REDIS_CODEF_ACCESS_TOKEN_TTL));

        log.info("\n\n**여권인증 액세스키 발급 완료**\n\n");
        return accessToken;
    }

}
