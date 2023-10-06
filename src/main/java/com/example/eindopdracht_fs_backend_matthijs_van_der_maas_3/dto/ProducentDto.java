package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

public class ProducentDto {

    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;

    @NotBlank

    private String owner;

    @NotBlank
    public String nameBrewery;
    @NotBlank
    public String saleLocation;
    public String street;

    public String houseNumber;

    public String zipcode;

    public String city;

    @NotBlank
    public String Brands;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String userName;

    @NotBlank
    public String password;

    @NotEmpty
    public Set<Role> roles = new HashSet<>();


    public String encode(String password){return password; }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNameBrewery() {
        return nameBrewery;
    }

    public void setNameBrewery(String nameBrewery) {
        this.nameBrewery = nameBrewery;
    }

    public String getSaleLocation() {
        return saleLocation;
    }

    public void setSaleLocation(String saleLocation) {
        this.saleLocation = saleLocation;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBrands() {
        return Brands;
    }

    public void setBrands(String brands) {
        Brands = brands;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }
}
