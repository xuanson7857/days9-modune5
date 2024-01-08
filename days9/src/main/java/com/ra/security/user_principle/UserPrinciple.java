package com.ra.security.user_principle;

import com.ra.model.entity.User;
import jakarta.persistence.Column;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPrinciple implements UserDetails {
    private Long id;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetails build(User user) {
        return UserPrinciple.builder().
                id(user.getId()).
                userName(user.getUserName()).
                password(user.getPassword()).
                authorities(user.getRoles().stream().map(item -> new SimpleGrantedAuthority(item.getName())).toList()).build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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