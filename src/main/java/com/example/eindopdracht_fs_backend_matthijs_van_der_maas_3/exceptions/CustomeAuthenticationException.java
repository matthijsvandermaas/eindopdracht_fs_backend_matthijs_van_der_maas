package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CustomeAuthenticationException extends AuthenticationException {
    public CustomeAuthenticationException(String msg) {
        super(msg);
    }

    public CustomeAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
