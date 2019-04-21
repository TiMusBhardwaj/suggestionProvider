package com.example.app;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.company.app.AppApplication;
import com.company.domain.configdata.repository.CityInfoRepository;

@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest(classes=AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppApplicationTests {

	@LocalServerPort
	private int port;
	
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	public void contextLoads() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.TEXT_PLAIN_VALUE);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort("/suggest_cities_count"))
		        .queryParam("start", "A")
		        .queryParam("res", -5);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        String.class);
		assertNotNull(response.getBody());
		

	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
