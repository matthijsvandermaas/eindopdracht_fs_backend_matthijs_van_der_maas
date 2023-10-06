package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model;


import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Producent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String first_Name;

    @NotBlank
    private String last_Name;

    @NotBlank
    private String owner;

    @NotBlank
    private String name_Brewery;

    @NotBlank
    private String street_Name;

    @NotBlank
    @Size(min = 1)
    private String house_Number;

    @NotBlank
    private String zipcode;

    @NotBlank
    private String city;

    @NotBlank
    private String brand_Name;

    @NotBlank
    private String sale_Location;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String user_Name;

    @NotBlank
    private String password;

    @OneToMany
    private List<Product> products;

    // Getter- en setter-methoden hier

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName_Brewery() {
        return name_Brewery;
    }

    public void setName_Brewery(String name_Brewery) {
        this.name_Brewery = name_Brewery;
    }

    public String getStreet_Name() {
        return street_Name;
    }

    public void setStreet_Name(String street_Name) {
        this.street_Name = street_Name;
    }

    public String getHouse_Number() {
        return house_Number;
    }

    public void setHouse_Number(String house_Number) {
        this.house_Number = house_Number;
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

    public String getBrand_Name() {
        return brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        this.brand_Name = brand_Name;
    }

    public String getSale_Location() {
        return sale_Location;
    }

    public void setSale_Location(String sale_Location) {
        this.sale_Location = sale_Location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter- en setter-methoden voor andere velden

}
