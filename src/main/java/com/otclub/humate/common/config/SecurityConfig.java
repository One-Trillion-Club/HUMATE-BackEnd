package com.otclub.humate.common.config;

import com.otclub.humate.domain.auth.jwt.JwtAuthenticationFilter;
import com.otclub.humate.domain.auth.jwt.JwtProvider;
import com.otclub.humate.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 스프링 시큐리티 Config 클래스
 * @author 조영욱
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	조영욱        최초 생성
 * 2024.07.30   조영욱        스프링 시큐리티 필터 체인 제외 URL 기능 추가
 * </pre>
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtTokenProvider;
    private final AuthService authService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests.requestMatchers("/auth/**").hasAuthority("ADMIN") // ADMIN 권한 필요 URL
                                .requestMatchers("/member/**").permitAll() // permitAll 해야 JwtAuthenticationFilter에 들어감
                                .anyRequest().permitAll()) // 이 코드가 있어야 hasAuthority("ADMIN") 한 코드도 JwtAuthenticationFilter에 들어감
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, authService), UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 시큐리티 필터 체인 제외 URL
        return (web) -> web.ignoring().requestMatchers(
                "/auth/signup","/auth/login",
//                "/auth/**",
                "/posts/**",
                "/activities/**",
                "/rooms/**",
                "/companions/**",
                "/reviews/**");
    }
}
