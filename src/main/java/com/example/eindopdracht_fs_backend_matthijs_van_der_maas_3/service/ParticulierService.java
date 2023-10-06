package com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.service;

import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.dto.ParticulierDto;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.model.Particulier;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.repository.ParticulierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticulierService {
    private final ParticulierRepository particulierRepository;

    public ParticulierService(ParticulierRepository particulierRepository) {
        this.particulierRepository = particulierRepository;
    }

    public List<Particulier> findAllParticulier() {
        return particulierRepository.findAll();
    }

    public Particulier createParticulier(ParticulierDto particulierDto) {
        Particulier newParticulier = new Particulier();
        newParticulier.setFirst_Name(particulierDto.getFirst_Name());
        newParticulier.setLast_Name(particulierDto.getLast_Name());
        newParticulier.setEmail(particulierDto.getEmail());
        newParticulier.setUser_Name(particulierDto.getUser_Name());
        newParticulier.setPassword(particulierDto.getPassword());

        // Sla het nieuwe particulier op in de repository
        particulierRepository.save(newParticulier);

        return newParticulier;
    }
}
