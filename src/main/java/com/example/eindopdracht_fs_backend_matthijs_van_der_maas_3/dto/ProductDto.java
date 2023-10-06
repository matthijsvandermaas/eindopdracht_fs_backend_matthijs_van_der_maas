package com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.dto;

import jakarta.validation.constraints.*;

public class ProductDto {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 128)
    private String name_Product;

    @NotBlank
    @Size(min = 1, max = 128)
    private String name_Producer;

    @NotNull
    @Positive
    private Double percentage;

    @NotBlank
    @Email
    private String email;

    @Size(min = 1, max = 128)
    private String color;

    @Size(min = 1, max = 128)
    private String tast;

    @NotNull
    @Positive
    private Double volume;

    @NotBlank
    @Size(min = 1, max = 128)
    private String location_Producer;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public void setLocation_Producer(String location_Producer) {
        this.location_Producer = location_Producer;
    }


    public String getLocation_Producent() {
        return location_Producer;
    }
}
