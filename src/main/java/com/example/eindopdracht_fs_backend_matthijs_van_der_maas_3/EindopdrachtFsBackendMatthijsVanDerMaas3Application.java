package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class EindopdrachtFsBackendMatthijsVanDerMaas3Application {
	public static void main(String[] args) {
		SpringApplication.run(EindopdrachtFsBackendMatthijsVanDerMaas3Application.class, args);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		String formattedDate = sdf.format(new Date());
		System.out.println("Formatted Date: " + formattedDate);

//
	}
}
