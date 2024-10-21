package com.curso.security;

import com.curso.domains.enums.PersonType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {
    private UUID id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(UUID id, String email, String senha, Set<PersonType> personTypes) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = personTypes.stream()
                .map(x -> new SimpleGrantedAuthority(x.getPersonType()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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
