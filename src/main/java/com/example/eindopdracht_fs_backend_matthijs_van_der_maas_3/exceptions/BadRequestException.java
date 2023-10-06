package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.exceptions;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public BadRequestException() {
        super();
    }
    public BadRequestException(String message) {
        super(message);
    }
}
