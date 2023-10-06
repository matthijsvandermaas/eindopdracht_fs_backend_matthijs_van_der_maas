package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ParticulierDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticulierService {

    private final ParticulierRepository particulierRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ParticulierService(ParticulierRepository particulierRepository, BCryptPasswordEncoder passwordEncoder) {
        this.particulierRepository = particulierRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createParticulier(ParticulierDto particulierDto) {
        // Het wachtwoord hashen voordat het wordt opgeslagen
        String hashedPassword = passwordEncoder.encode(particulierDto.getPassword());

        // Nieuw Particulier object maken
        Particulier newParticulier = new Particulier();
        newParticulier.setFirstName(particulierDto.getFirstName());
        newParticulier.setLastName(particulierDto.getLastName());
        newParticulier.setEmail(particulierDto.getEmail());
        newParticulier.setUserName(particulierDto.getUserName());
        newParticulier.setPassword(hashedPassword);
        newParticulier.setAuthorities(particulierDto.getAuthority());

        particulierRepository.save(newParticulier);

        // Terugkeren met een bevestigingsbericht
        return "Inschrijving succesvol! Particulier ID: " + newParticulier.getId();
    }

    public List<Particulier> getAllParticulieren() {
        return particulierRepository.findAll();
    }

    public Particulier getParticulierById(long id) {
        Optional<Particulier> particulierOptional = particulierRepository.findById(String.valueOf(id));
        return particulierOptional.orElse(null);
    }

    public void updateParticulier(Long id, ParticulierDto particulierDto) {
        Optional<Particulier> existingParticulierOptional = particulierRepository.findById(String.valueOf(id));
        if (existingParticulierOptional.isPresent()) {
            Particulier existingParticulier = existingParticulierOptional.get();
            existingParticulier.setFirstName(particulierDto.getFirstName());
            existingParticulier.setLastName(particulierDto.getLastName());
            existingParticulier.setEmail(particulierDto.getEmail());
            existingParticulier.setUserName(particulierDto.getUserName());
            existingParticulier.setPassword(passwordEncoder.encode(particulierDto.getPassword()));
            existingParticulier.setAuthorities(particulierDto.getAuthority());

            particulierRepository.save(existingParticulier);
        } else {
            throw new IllegalArgumentException("Particulier met ID " + id + " niet gevonden");
        }
    }

    public void deleteParticulier(Long id) {
        particulierRepository.deleteById(String.valueOf(id));
    }
}
