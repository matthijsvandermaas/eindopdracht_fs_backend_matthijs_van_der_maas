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

        // Een nieuwe Particulier maken en gegevens instellen
        Particulier newParticulier = new Particulier();
        newParticulier.setFirstName(particulierDto.getFirstName());
        newParticulier.setLastName(particulierDto.getLastName());
        newParticulier.setEmail(particulierDto.getEmail());
        newParticulier.setUserName(particulierDto.getUserName());
        newParticulier.setPassword(hashedPassword);


        // Het nieuwe particulierobject opslaan in de database
        particulierRepository.save(newParticulier);

        // Terugkeren met een bevestigingsbericht
        return "Inschrijving succesvol! Particulier ID: " + newParticulier.getId();
    }


    private String hashPassword(String password) {
        // Hier kun je de logica voor het hashen van het wachtwoord toevoegen
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}