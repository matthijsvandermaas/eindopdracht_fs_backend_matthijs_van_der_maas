package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service;


import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ProducentDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Producent;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ProducentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducentService {
    private final ProducentRepository producentRepository;

    public ProducentService(ProducentRepository producentRepository) {
        this.producentRepository = producentRepository;
    }
    public List<Producent> findAllProducts(){
        return producentRepository.findAll();
    }
    public Producent createProducent(ProducentDto producent) {
        Producent newProducent = new Producent();
        newProducent.setFirst_Name(producent.getFirst_Name());
        newProducent.setLast_Name(producent.getLast_Name());
        newProducent.setOwner(producent.getOwner());
        newProducent.setName_Brewery(producent.getName_Brewery());
        newProducent.setHouse_Number(producent.getHouse_Number());
        newProducent.setZipcode(producent.getZipcode());
        newProducent.setCity(producent.getCity());
        newProducent.setBrand_Name(producent.getBrand_Name());
        newProducent.setSale_Location(producent.getSale_Location());
        newProducent.setEmail(producent.getEmail());
        newProducent.setUser_Name(producent.getUser_Name());
        newProducent.setPassword(producent.getPassword());

        // Sla het nieuwe product op in de repository
        producentRepository.save(newProducent);

        return newProducent;
    }
}
