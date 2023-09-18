package com.example.demoGraphClient;

import com.example.demoGraphClient.client.CountryClient;
import com.example.demoGraphClient.data.CountryDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class DemoGraphClientApplication {

	public static void main(String[] args) throws IOException {

		ConfigurableApplicationContext context = SpringApplication.run(DemoGraphClientApplication.class, args);
		CountryClient client = (CountryClient) context.getBean("countryClient");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		CountryDto countryDto = client.getCountryDetails("BE");
		log.info(ow.writeValueAsString(countryDto));

	}
}
