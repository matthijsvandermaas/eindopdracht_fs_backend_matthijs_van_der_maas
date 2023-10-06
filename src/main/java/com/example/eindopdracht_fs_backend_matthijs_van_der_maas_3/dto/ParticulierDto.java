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
    public String firstName;
    @NotBlank
    @Size(min = 1, max = 128)
    public String lastName;

    @NotBlank
    @Email
    public String email;

    @Size(min = 1, max = 128)
    public String userName;

    @Size(min = 1, max = 128)
    public String password;
    @NotBlank
    public String roles;

    public ParticulierDto mapToDto(Particulier particulier) {
        ParticulierDto dto = new ParticulierDto();
        dto.setId(particulier.getId());
        dto.setFirstName(particulier.getFirstName());
        dto.setLastName(particulier.getLastName());
        dto.setEmail(particulier.getEmail());
        dto.setPassword(particulier.getPassword());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setRoles(String roles) {
        this.roles = roles;
    }


    public String getRoles() {
        return roles;
    }
}