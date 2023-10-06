package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ParticulierDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticulierService {
    private final ParticulierRepository particulierRepository;

    public ParticulierService(ParticulierRepository particulierRepository) {
        this.particulierRepository = particulierRepository;
    }
    public Particulier findById(Long id) {
        Optional<Particulier> particulier = particulierRepository.findById(id);
        return particulier.orElse(null);
    }


    public Particulier createParticulier(ParticulierDto particulierDto) {
        try {
            Particulier particulier = new Particulier(particulierDto.first_Name, particulierDto.last_Name, particulierDto.email, particulierDto.user_Name, particulierDto.password);
            particulier.setFirst_Name(particulierDto.getFirst_Name());
            particulier.setLast_Name(particulierDto.getLast_Name());
            particulier.setEmail(particulierDto.getEmail());
            particulier.setUser_Name(particulierDto.getUser_Name());
            particulier.setPassword(particulierDto.getPassword());

            // Sla het nieuwe particulier op in de repository
            Particulier savedParticulier = particulierRepository.save(particulier);

            return savedParticulier;
        }catch (Exception e){
            throw new RuntimeException("Er is een fout opgetreden tijdens het verwerken van de inschrijving: " + e.getMessage());
        }
        }


    public List<ParticulierDto> findByIds(List<Long> ids) {
        List<ParticulierDto> result = new ArrayList<>();
        for (Long id : ids) {
            Optional<Particulier> particulier = particulierRepository.findById(id);
            particulier.ifPresent(p -> result.add(mapToDto(p)));
        }
        return result;
    }

    private ParticulierDto mapToDto(Particulier particulier) {
        ParticulierDto dto = new ParticulierDto();
        // Mapping logica om Particulier naar ParticulierDto om te zetten
        dto.setId(particulier.getId());
        dto.setFirst_Name(particulier.getFirst_Name());
        dto.setLast_Name(particulier.getLast_Name());
        dto.setEmail(particulier.getEmail());
        dto.setUser_Name(particulier.getUser_Name());
        dto.setPassword(particulier.getPassword());
        return dto;
    }


}
