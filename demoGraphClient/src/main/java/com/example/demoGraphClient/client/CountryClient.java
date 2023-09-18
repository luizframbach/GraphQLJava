package com.example.demoGraphClient.client;

import com.example.demoGraphClient.data.CountryDto;
import com.example.demoGraphClient.data.GraphqlRequestBody;
import com.example.demoGraphClient.util.GraphqlSchemaReaderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
@Slf4j
public class CountryClient {

	private final String url;

	public CountryClient(@Value("https://countries.trevorblades.com/") String url) {
		this.url = url;
	}

	public CountryDto getCountryDetails(final String countryCode) throws IOException {

		WebClient webClient = WebClient.builder().build();

		GraphqlRequestBody graphQLRequestBody = new GraphqlRequestBody();

		final String query = GraphqlSchemaReaderUtil.getSchemaFromFileName("getCountryDetails");
		final String variables = GraphqlSchemaReaderUtil.getSchemaFromFileName("variables");

		graphQLRequestBody.setQuery(query);
		graphQLRequestBody.setVariables(variables.replace("countryCode", countryCode));

		return webClient.post()
			.uri(url)
			.bodyValue(graphQLRequestBody)
			.retrieve()
			.bodyToMono(CountryDto.class)
			.block();
	}
}
