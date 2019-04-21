package com.company.app.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.company.app.service.CityService;

/**
 * Rest Controller City Related information
 * 
 * @author sumit.bhardwaj
 *
 */
@RestControllerAdvice
@RestController
@Validated
public class AppController {

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	private CityService wcService;

	@GetMapping(path = "/suggest_cities_count", produces=MediaType.TEXT_PLAIN_VALUE )
	public String suggestCities(@Validated@RequestParam("start") @NotNull @NotBlank String prefix,
			@Validated()@RequestParam("res") @Min(1) @Max(Integer.MAX_VALUE) int count) {
		logger.info("Request received with paramters -> start : {{}} , requested result count : {}", prefix, count);
		List<String> suggestionList = wcService.suggestCities(prefix, count);
		logger.info("Response count : {}", suggestionList.size());
		logger.debug("Response List Contains : {}", suggestionList);
		return String.join(System.lineSeparator(), suggestionList);
	}

}
