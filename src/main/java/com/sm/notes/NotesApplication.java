package com.sm.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Properties;

@SpringBootApplication
@EnableJpaAuditing
public class NotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesApplication.class, args);

		SpringApplication application = new SpringApplication(NotesApplication.class);

		String port = "3306";

		Properties properties = new Properties();
		if (args.length > 0)
			port = "jdbc:mysql://localhost:" + port + "/notes_app?useSSL=false";

		properties.put("spring.datasource.url", port);

		application.setDefaultProperties(properties);

		application.run(args);
	}

}

