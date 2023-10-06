package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public interface Userdetails {
    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();

    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
