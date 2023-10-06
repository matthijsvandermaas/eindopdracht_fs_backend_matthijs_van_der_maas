package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication
public class EindopdrachtFsBackendMatthijsVanDerMaas3Application {
	public static void main(String[] args) {
//		SpringApplication.run(EindopdrachtFsBackendMatthijsVanDerMaas3Application.class, args);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		String formattedDate = sdf.format(new Date());
		System.out.println("Formatted Date: " + formattedDate);

//		String jdbcURL = "jdbc:postgresql://localhost:5432/eindproject_fs_matthijsvandermaas";
//		String username = "postgres";
//		String password = "password";
//		try {
//			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
//			System.out.println("Connected to PostgreSQL server");
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("Error in connecting to PostgreSQL server");
//		}
//	}

//	@Override
//	public void run(String... args) throws Exception {
//		String sql = "INSERT INTO particulieren (first_name, last_name, email, username, password) VALUES ('"
//				+ Particulier.getFirst_Name() + "', '"
//				+ Particulier.getLast_Name() + "', '"
//				+ Particulier.getEmail() + "', '"
//				+ Particulier.getUser_Name() + "', '"
//				+ Particulier.getPassword() + "')";
//
//		JdbcTemplate jdbcTemplate = null;
//		int rows = jdbcTemplate.update(sql);
//		if (rows > 0) {
//			System.out.println("a new row has been inserted");
//		}
//		System.out.println("De CommandLineRunner wordt uitgevoerd...");
//
//		// Maak een JdbcTemplate instantie aan en voer een SQL-query uit
//		String jdbcURL = "jdbc:postgresql://localhost:5432/eindproject_fs_matthijsvandermaas";
//		String username = "postgres";
//		String password = "password";
//		jdbcTemplate = new JdbcTemplate();
//		jdbcTemplate.setDataSource(new DriverManagerDataSource(jdbcURL, username, password));

//	public void run(String... args) throws Exception {
//		System.out.println("De CommandLineRunner wordt uitgevoerd...");
//		private jdbcTemplate jdbcTemplate;
//		// Voorbeeld: voer een SQL-query uit
	}
}
