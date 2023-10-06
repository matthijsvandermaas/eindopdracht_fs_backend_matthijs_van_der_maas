package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

        import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
        import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Role;
        import org.springframework.security.core.GrantedAuthority;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.userdetails.UserDetails;

        import java.util.Collection;
        import java.util.HashSet;
        import java.util.Set;

public class ParticulierDetails implements Userdetails {

    private final Particulier particulier;

    public ParticulierDetails(Particulier particulier) {
        this.particulier = particulier;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : particulier.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
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
        return particulier.isEnabled();
    }
}
