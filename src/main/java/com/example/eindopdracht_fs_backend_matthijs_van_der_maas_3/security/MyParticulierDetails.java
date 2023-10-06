package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;




public class MyParticulierDetails implements  ParticulierDetails{

    private final Particulier particulier;

    public MyParticulierDetails(Particulier particulier) { this.particulier = particulier;}

    @Override
    public List<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new List<>();

        for(Role role : particulier.getRoles()) {
        authorities.add(new SimpleGrantedAuthority(role.getRolename()));
        }

        return authorities;

    }
    @Override
    public  String getPassword() { return particulier.getPassword();
    }
    @Override
    public  String getUsername() { return particulier.getUserName();
    }

    @Override
    public  boolean isAccountNonExpired() { return true;
    }

    @Override
    public  boolean isAccountNonLocked() { return true;
    }
    @Override
    public  boolean isCredentialsNonExpired() { return true;
    }
    @Override
    public  boolean isEnabled() { return true;
    }
}
