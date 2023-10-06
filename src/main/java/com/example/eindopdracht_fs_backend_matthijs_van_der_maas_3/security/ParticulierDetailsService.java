package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class ParticulierDetailsService {

    private final ParticulierRepository particulierRepository;
    private String username;

    public ParticulierDetailsService(ParticulierRepository repository) {
        this.particulierRepository = repository;
    }
    public ParticulierDetails particulierDetails(String username) throws UsernameNotFoundException {
        this.username = username;
        Optional<Particulier> p = particulierRepository.findById(username);
        if (p.isPresent()) {
            Particulier particulier = p.get();
            return new ParticulierDetails(particulier) {
                @Override
                public List<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return null;
                }

                @Override
                public String getUsername() {
                    return null;
                }

                @Override
                public boolean isAccountNonExpired() {
                    return false;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return false;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return false;
                }

                @Override
                public boolean isEnabled() {
                    return false;
                }
            };
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public String loadUserByUsername(String username) {
        return username;
    }
}
