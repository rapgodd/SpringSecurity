package com.example.springsecuritytest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 이거를 통해 사용자 요청 비밀번호 암호화 한다.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        /**
         * 특정 권한 있는 사람들만 특정 페이지 접근 가능
         */
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc","/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

        /**
         * 권한 없는 사용자 요청 -> 로그인 페이지로 리다이렉션
         */
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );
        //"/loginProc" 로그인 폼 클릭하고 "/loginProc"이 경로로 오게 하면 시큐리티가 처리해줌

        //enable하는 방법 11강 csrf enable 설정 들어
        http
                .csrf((auth) -> auth.disable());

        /**
         * 동시접속시
         * true : 초과시 새로운 로그인 차단
         * false : 초과시 기존 세션 하나 삭제
         */
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));



        /**
         * sessionManagement().sessionFixation().none() : 로그인 시 세션 정보 변경 안함
         * sessionManagement().sessionFixation().newSession() : 로그인 시 세션 새로 생성
         * sessionManagement().sessionFixation().changeSessionId() : 로그인 시 동일한 세션에 대한 id 변경
         */
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());



        return http.build();
    }

}