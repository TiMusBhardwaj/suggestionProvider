package com.company.app.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.app.service.WorldCityService;

/**
 * Rest Controller City Related information
 * 
 * @author sumit.bhardwaj
 *
 */
@RestController
public class AppController {

	private static final Logger logger = LoggerFactory.getLogger(AppController.class);

	@Autowired
	private WorldCityService wcService;


	@GetMapping(path = "/suggest_cities_count")
	@Validated
	public List<String> suggestCities(@RequestParam("start") @NotNull String prefix,
			@RequestParam("res") @Min(1) @Max(Integer.MAX_VALUE) int count) {
		logger.info("Request received with parma -> start : {} , requested result count : {}", prefix, count);
		return wcService.suggestCities(prefix, count);
	}

}
