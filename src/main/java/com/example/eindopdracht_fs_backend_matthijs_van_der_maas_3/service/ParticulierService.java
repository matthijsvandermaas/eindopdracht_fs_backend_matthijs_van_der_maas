package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ParticulierDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ParticulierService {

    private final ParticulierRepository particulierRepository;

    public ParticulierService(ParticulierRepository particulierRepository) {
        this.particulierRepository = particulierRepository;
    }
    public String createParticulier(ParticulierDto particulierDto) {

        // Het wachtwoord hashen voordat het wordt opgeslagen
        String hashedPassword = hashPassword(particulierDto.getPassword());

        Particulier newParticulier = new Particulier();
        newParticulier.setFirstName(particulierDto.getFirstName());
        newParticulier.setLastName(particulierDto.getLastName());
        newParticulier.setEmail(particulierDto.getEmail());
        newParticulier.setUserName(particulierDto.getUserName());
        newParticulier.setPassword(hashedPassword);
        newParticulier.setRoles(particulierDto.getRoles());



        // Het nieuwe particulier object opslaan in de database
        particulierRepository.save(newParticulier);

        // Terugkeren met een bevestigingsbericht
        return "Inschrijving succesvol! Particulier ID: " + newParticulier.getId();
    }


    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
    public void saveParticulier(Particulier particulier) {
        String firstName = particulier.getFirstName();
        String lastName = particulier.getLastName();
        String email = particulier.getEmail();
        String username = particulier.getUserName();
        // Hash het wachtwoord voordat je het opslaat
        String rawPassword = particulier.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);
        particulier.setPassword(hashedPassword);

    }
}