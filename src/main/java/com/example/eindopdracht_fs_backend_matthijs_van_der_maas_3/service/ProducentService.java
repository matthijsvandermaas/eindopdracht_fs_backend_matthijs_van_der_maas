package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service;


import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ProducentDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Producent;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ProducentRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProducentService {
    private final ProducentRepository producentRepository;

    public ProducentService(ProducentRepository producentRepository){
        this.producentRepository = producentRepository;
    }

    public String createProducent(ProducentDto producentDto){

        // Het wachtwoord hashen voordat het wordt opgeslagen
        String hashedPassword = hashPassword(producentDto.getPassword());

        // Een nieuwe Producent maken en gegevens instellen
        Producent newProducent = new Producent();

        newProducent.setFirstName(producentDto.getFirstName());
        newProducent.setLastName(producentDto.getLastName());
        newProducent.setOwner(producentDto.getOwner());
        newProducent.setNameBrewery(producentDto.getNameBrewery());
        newProducent.setSaleLocation(producentDto.getSaleLocation());
        newProducent.setStreet(producentDto.getStreet());
        newProducent.setHouseNumber(producentDto.getHouseNumber());
        newProducent.setZipcode(producentDto.getZipcode());
        newProducent.setCity(producentDto.getCity());
        newProducent.setBrands(producentDto.getBrands());
        newProducent.setEmail(producentDto.getEmail());
        newProducent.setUserName(producentDto.getUserName());
        newProducent.setPassword(producentDto.getPassword());
        newProducent.setRoles(producentDto.getRoles());


        // Het nieuwe producent object opslaan in de database
        producentRepository.save(newProducent);

        // Terugkeren met een bevestigingsbericht
        return "Inschrijving succesvol! Producent ID: " + newProducent.getId();
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void saveProducent(Producent producent) {
        String firstName = producent.getFirstName();
        String lastName = producent.getLastName();
        String owner = producent.getOwner();
        String nameBrewery = producent.getNameBrewery();
        String saleLocation = producent.getSaleLocation();
        String street = producent.getStreet();
        String houseNumber = producent.getHouseNumber();
        String zipcode = producent.getZipcode();
        String city = producent.getCity();
        String brands = producent.getBrands();
        String email = producent.getEmail();
        String username = producent.getUserName();
        // Hash het wachtwoord voordat je het opslaat
        String rawPassword = producent.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);
        producent.setPassword(hashedPassword);

    }
}
