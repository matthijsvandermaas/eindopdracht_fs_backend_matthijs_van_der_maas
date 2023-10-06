package com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.dto;

import jakarta.validation.constraints.*;

public class ProducentDto {

    private Long id;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    private String First_Name;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    private String Last_Name;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    private String Owner;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    private String Name_Brewery;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    private String Street_Name;

    @NotBlank
    @Min(1)
    @Size(min = 1, max = 128)
    private String House_Number;

    @NotBlank
    @Pattern(regexp = "^([A-HK-PRSVWY][A-HJ-PR-Y])\\s?([0][2-9]|[1-9][0-9])\\s?[A-HJ-PR-Z]{3}$")
    private String Zipcode;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    private String Sale_Location;

    @NotBlank
    @Email
    private String Email;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    private String City;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    private String Brand_Name;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    private String User_Name;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    private String Password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getName_Brewery() {
        return Name_Brewery;
    }

    public void setName_Brewery(String name_Brewery) {
        Name_Brewery = name_Brewery;
    }

    public String getStreet_Name() {
        return Street_Name;
    }

    public void setStreet_Name(String street_Name) {
        Street_Name = street_Name;
    }

    public String getHouse_Number() {
        return House_Number;
    }

    public void setHouse_Number(String house_Number) {
        House_Number = house_Number;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getSale_Location() {
        return Sale_Location;
    }

    public void setSale_Location(String sale_Location) {
        Sale_Location = sale_Location;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getBrand_Name() {
        return Brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        Brand_Name = brand_Name;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
