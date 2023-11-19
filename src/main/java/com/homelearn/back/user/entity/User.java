package com.homelearn.back.user.entity;

import com.homelearn.back.oauth.OAuthProvider;
import com.homelearn.back.user.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class User implements UserDetails {
    private Long id;
    private String password;
    private String email;
    private String name;
    private UserRole role; // UserRole은 사용자 역할을 나타내는 열거형으로 가정합니다.
    private OAuthProvider provider;

    // UserDetails 인터페이스의 메서드 구현

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> "ROLE_" + role.name());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // 사용자 식별에 email을 사용합니다.
    }

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
}
