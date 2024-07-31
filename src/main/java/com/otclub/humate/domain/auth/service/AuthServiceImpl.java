package com.otclub.humate.domain.auth.service;

import com.otclub.humate.common.entity.Member;
import com.otclub.humate.common.exception.CustomException;
import com.otclub.humate.common.exception.ErrorCode;
import com.otclub.humate.domain.auth.dto.GeneratePhoneVerificationCodeRequestDTO;
import com.otclub.humate.domain.auth.dto.LogInRequestDTO;
import com.otclub.humate.domain.auth.dto.SignUpRequestDTO;
import com.otclub.humate.domain.auth.jwt.JwtDTO;
import com.otclub.humate.domain.auth.jwt.JwtGenerator;
import com.otclub.humate.domain.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

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
 * </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

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

    public String generatePhoneVerificationCode(GeneratePhoneVerificationCodeRequestDTO dto) {
        String phone = dto.getPhone();

        Random random = new Random(123122);
        // 6자리 랜덤 숫자 생성
        int randomNumber = random.nextInt(1000000);

        // 6자리 문자열로 포맷팅 (앞자리 0 포함)
        String code = String.format("%06d", randomNumber);

        return code;
    }
}
