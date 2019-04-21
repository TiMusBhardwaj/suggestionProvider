package com.example.app.service;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.company.app.service.impl.AutoCompleteServiceImpl;
import com.company.domain.configdata.repository.CityInfoRepository;

@RunWith(MockitoJUnitRunner.class)
public class AutoCompleteServiceTest {

	@Mock
	private CityInfoRepository repo;
	
	
	@InjectMocks
	private AutoCompleteServiceImpl service;
	
	@Test
	public void setUpTest() {
		List<String> list = Arrays.asList("Delhi", "Mumbai", "Kolkata", " Mulatan", "Masim Pur", null," ");
		
		Mockito.when(repo.getAllCities()).thenReturn(list.stream());
		service.intilaize();
		List<String> suggestions = service.getSuggestion("Mu", 3);
		assertTrue(suggestions.contains("MUMBAI") && suggestions.contains("MULATAN") && suggestions.size() == 2);
		
	}
	
	@Test
	public void trieSizeTest() {
		List<String> list = Arrays.asList("Delhi", "Mumbai", "Kolkata", "Mulatan", "Masim Pur", null," ", "Mulatan");
		
		Mockito.when(repo.getAllCities()).thenReturn(list.stream());
		service.intilaize();
		assertTrue(service.getTrieSize() == 5); 		
	}

}
