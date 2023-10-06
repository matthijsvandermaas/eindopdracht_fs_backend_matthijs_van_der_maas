package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyParticulierDetailsService implements  ParticulierDetailService {

    private final ParticulierRepository particulierRepository;

    public MyParticulierDetailsService(ParticulierRepository repository) {
        this.particulierRepository = repository;
    }

    @Override
    public MyParticulierDetails loadParticulierByUsername(String username) throws UsernameNotFoundException {
        Optional<Particulier> p = particulierRepository.findById(username);
        if (p.isPresent()) {
            Particulier particulier = p.get();
            return new MyParticulierDetails(particulier);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

}
