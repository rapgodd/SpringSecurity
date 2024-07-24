package com.example.springsecuritytest.Controller;

import com.example.springsecuritytest.dto.CustomUserDetails;
import com.example.springsecuritytest.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {
    /**
     * 이렇게 하면 현재 요청을 보내는 사람의 정보를 담아서
     * 프론트앤드 개발자들에게 HTTP 응답으로 보낼 수 있음.
     */
    @GetMapping("/")
    public String mainP(Model model) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        /**
         * 아 부분은 유저의 Role을 가져오는 부분이다.
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        //


        model.addAttribute("id", id);
        model.addAttribute("role", role);
        return "main";
    }

//    @GetMapping("/api")
//    public UserEntity main1P() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        UserEntity principal = (UserEntity) authentication.getPrincipal();
////        int id = principal.getId();
////        String role = principal.getRole();
//
//
//
//        return principal;
//    }
}
