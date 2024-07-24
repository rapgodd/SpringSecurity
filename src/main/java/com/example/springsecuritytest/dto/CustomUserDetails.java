package com.example.springsecuritytest.dto;

import com.example.springsecuritytest.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * UserDetailService에서 정보를 받았으면 그 정보를 적절하게 변형해서 Config에 전달한다.
 */
public class CustomUserDetails implements UserDetails {

    private UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * 아래 4개 메서드는 엔티티에서 정의하지 않은 부분이기에 간단하게 리턴값에
     * 그냥 아직 만료, 끝나지 않았다라고 할것이다.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Role값에 대해서 반환하는 메소드.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return userEntity.getRole(); //실무에서는 이 부분만 다를 것이다.
                                            // 이 부분은 UserEntity에서 Role을 가져온다.
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }
}
