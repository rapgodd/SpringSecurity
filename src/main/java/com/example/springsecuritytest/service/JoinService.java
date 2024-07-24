package com.example.springsecuritytest.service;


import com.example.springsecuritytest.dto.JoinDTO;
import com.example.springsecuritytest.entity.UserEntity;
import com.example.springsecuritytest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void joinProcess(JoinDTO joinDTO) {


        //db에 이미 동일한 username을 가진 회원이 존재하는지?
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if (isUser) {
            return;
        }


        UserEntity data = new UserEntity();
        /**
         * 이렇게 하면 회원가입할때 모두 User라는 권한을 가지게 된다.
         */
        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_ADMIN");


        userRepository.save(data);
    }
}
