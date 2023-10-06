package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto;


import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ParticulierDto {
    @Id
    @NotBlank
    public Long id;
    @NotBlank
    @Size(min = 1, max = 128)
    public String first_Name;
    @NotBlank
    @Size(min = 1, max = 128)
    public String last_Name;

    @NotBlank
    @Email
    public String email;

    @Size(min = 1, max = 128)
    public String user_Name;

    @Size(min = 1, max = 128)
    public String password;

 

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public String getEmail() {
        return email;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public String getPassword() {
        return password;
    }

    public ParticulierDto mapToDto(Particulier particulier ){
       ParticulierDto dto = new ParticulierDto();
        dto.setId(particulier.getId());
        dto.setFirst_Name(particulier.getFirst_Name());
        dto.setLast_Name(particulier.getLast_Name());
        dto.setEmail(particulier.getEmail());
        // Andere velden ook mappen indien nodig
        return dto;
    }

    public void setId(Long id) {
    }
}
