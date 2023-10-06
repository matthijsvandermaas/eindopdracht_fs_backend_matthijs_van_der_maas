package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParticulierDetails implements Userdetails {
    private List<GrantedAuthority> authorities;
    public void setAuthorities(List<String> roles) {
        this.authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Rollen moeten met 'ROLE_'-prefix worden toegevoegd volgens Spring Security-conventie
                .collect(Collectors.toList());
    }

    private final Particulier particulier;

    public ParticulierDetails(Particulier particulier) {
        this.particulier = particulier;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : particulier.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return particulier.getPassword();
    }

    @Override
    public String getUsername() {
        return particulier.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Implementeer logica voor accountverloop hier indien nodig
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implementeer logica voor vergrendeld account hier indien nodig
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implementeer logica voor vervallen verwijzingen hier indien nodig
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Particulier.isEnabled();
    }
}
