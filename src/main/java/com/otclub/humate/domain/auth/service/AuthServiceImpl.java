package com.otclub.humate.domain.auth.service;

import com.otclub.humate.common.entity.Member;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.auth.dto.GeneratePhoneVerificationCodeRequestDTO;
import com.otclub.humate.domain.auth.dto.LogInRequestDTO;
import com.otclub.humate.domain.auth.dto.PhoneVerificateRequestDTO;
import com.otclub.humate.domain.auth.dto.SignUpRequestDTO;
import com.otclub.humate.domain.auth.jwt.JwtDTO;
import com.otclub.humate.domain.auth.jwt.JwtGenerator;
import com.otclub.humate.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

import static com.otclub.humate.domain.auth.util.AuthUtil.REDIS_PHONE_VERIFICATION_KEY_TTL;

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
 * </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final StringRedisTemplate redisTemplate;

    /**
     * 새 사용자 회원 가입
     * 로그인 id, 닉네임 중복 시 실패한다.
     *
     * @author 조영욱
     * @param dto 회원가입 사용자 정보
     */
    @Override
    public int signUp(SignUpRequestDTO dto) {
        log.info(dto.toString());

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

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
        Member member = mapper.selectMemberById(memberId);

        if (member == null || !refreshToken.equals(member.getRefreshToken())) {
            throw new Exception();
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
        int randomNumber = random.nextInt(1000000);

        // 6자리 문자열로 포맷팅 (앞자리 0 포함)
        String code = String.format("%06d", randomNumber);

        // 레디스에 코드 저장 (ttl 설정 포함)
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String redisCodeKey = "code:" + phone;

        // 레디스에 저장되는 구조: { "code:01012341234" : "012345" }
        operations.set(redisCodeKey, code, Duration.ofSeconds(REDIS_PHONE_VERIFICATION_KEY_TTL));

        return code;
    }

    /**
     * 휴대폰 번호 인증
     *
     * @author 조영욱
     */
    public boolean phoneVerificate(PhoneVerificateRequestDTO dto) {
        String phone = dto.getPhone();
        String code = dto.getCode();

        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String redisCodeKey = "code:" + phone;

        String cachedCode = operations.get(redisCodeKey);

        // 해당 핸드폰 번호에 대해 코드 없을 시 false 리턴
        if (cachedCode == null) {
            log.info("\n해당 핸드폰 번호에 대해 캐시된 코드 없어서 인증 실패");
            return false;
        }

        if (code.equals(cachedCode)) { // 레디스에 저장된 코드와 같을 시 통과
            redisTemplate.delete(redisCodeKey); // 존재하던 키 삭제

            String redisVerificationSuccessKey = "verification:" + phone;
            // 레디스에 저장되는 구조: { "verification:01012341234" : "012345" }
            operations.set(redisVerificationSuccessKey, code, Duration.ofSeconds(REDIS_PHONE_VERIFICATION_KEY_TTL));
            log.info("\n휴대폰 인증 성공");
            return true;
        } else {
            log.info("\n저장된 코드와 달라서 인증 실패");
            return false;
        }
    }
}
