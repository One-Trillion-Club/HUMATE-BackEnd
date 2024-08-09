package com.otclub.humate.domain.auth.service;

import com.otclub.humate.domain.auth.dto.*;
import com.otclub.humate.domain.auth.jwt.JwtDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 인증/인가 서비스 인터페이스
 * @author 조영욱
 * @since 2024.07.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.30  	조영욱        최초 생성
 * 2024.07.30   조영욱        JWT 토큰 리프레시 추가
 * 2024.07.31   조영욱        휴대폰 인증 추가
 * 2024.07.31   조영욱        여권 인증 추가
 * 2024.08.05   조영욱        SMS 연동 추가
 * </pre>
 */
public interface AuthService {
    /**
     * 새 사용자 회원 가입
     * 로그인 id, 닉네임 중복 시 실패한다.
     *
     * @author 조영욱
     * @param dto 회원가입 사용자 정보
     */
    int signUp(SignUpRequestDTO dto, MultipartFile image);

    /**
     * loginId 중복 체크
     *
     * @author 조영욱
     * @param loginId 중복 확인 할 id
     */
    boolean checkAvailableLoginId(String loginId);

    /**
     * 회원 로그인
     *
     * @author 조영욱
     * @param dto 아이디, 패스워드
     */
    JwtDTO logIn(LogInRequestDTO dto);

    /**
     * JWT 토큰 리프레시
     * 액세스 토큰 만료 시 리프레시 토큰을 통한 토큰 리프레시
     *
     * @author 조영욱
     * @param memberId 유저 id
     * @param refreshToken validate 된 리프레시 토큰 값
     */
    JwtDTO refreshJwtToken(String memberId, String refreshToken) throws Exception;

    /**
     * 휴대폰 인증 번호 생성
     *
     * @author 조영욱
     * @return 인증 코드
     */
    String generatePhoneVerificationCode(GeneratePhoneVerificationCodeRequestDTO dto);

    /**
     * 휴대폰 번호 인증
     *
     * @author 조영욱
     * @return 성공 시 인증 성공을 보증하기 위한 key 리턴, 실패 시 null
     */
    String verifyPhone(PhoneVerificationRequestDTO dto);

    /**
     * 여권 번호 인증
     *
     * @author 조영욱
     * @return 성공 시 인증 성공을 보증하기 위한 key 리턴, 실패 시 null
     */
    String verifyPassport(PassportVerificationRequestDTO dto);
}
