package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.Security;


import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.User;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepos;

    public MyUserDetailsService(UserRepository repos) {
        this.userRepos = repos;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> ou = userRepos.findById((username));
        if (ou.isPresent()) {
            User user = ou.get();
            return new MyUserDetails(user);
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
