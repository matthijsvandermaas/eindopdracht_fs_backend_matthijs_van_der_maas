package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
//import javax.persistence.Entity;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producten;
    @NotBlank
    private String name_Product;

    @NotBlank
    private String name_Producer;

    @NotBlank
    @Max(15)
    private Double percentage;

    @NotBlank
    private String color;

    @NotBlank
    private String tast;

    @NotBlank
    @Max(1000)
    private Double volume;

    @NotBlank
    private String location_Producent;

    @ManyToOne
    private Producent producent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_Product() {
        return name_Product;
    }

    public void setName_Product(String name_Product) {
        this.name_Product = name_Product;
    }

    public String getName_Producer() {
        return name_Producer;
    }

    public void setName_Producer(String name_Producer) {
        this.name_Producer = name_Producer;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTast() {
        return tast;
    }

    public void setTast(String tast) {
        this.tast = tast;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getLocation_Producent() {
        return location_Producent;
    }

    public void setLocation_Producent(String location_Producent) {
        this.location_Producent = location_Producent;
    }
}
