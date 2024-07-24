package com.example.springsecuritytest.service;

import com.example.springsecuritytest.dto.CustomUserDetails;
import com.example.springsecuritytest.entity.UserEntity;
import com.example.springsecuritytest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * SecurityConfig의 검증 역할을 도와줌
 * SecurityConfig에서 유저 이름을 알려주면 그 정보를 바탕으로 리포지토리에서
 * 해당하는 객체가 있으면 CustomUserDetails DTO에 담아서 SecurityConfig에 반환한다.
 */
@Service
public class CustonUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 여기서 인자는 유저가 입력한 username이다
     * 그러면 이 username을 db에 조회한다.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity byUsername = userRepository.findByUsername(username);

        if (byUsername != null) {

            return new CustomUserDetails(byUsername);
        }

        return null;
    }


}
