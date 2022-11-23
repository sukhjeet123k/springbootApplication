package com.country.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.country.entity.Country;
import com.country.service.CountryService;

@RestController
@RequestMapping("/api")
public class CountyController {

	@Autowired
	private CountryService countryService;

	@GetMapping("countries")
	public ResponseEntity getCountries(@RequestParam(value = "page") int pageNo,
			@RequestParam(value = "size") int pageSize, @RequestParam(value = "q", required = false) String searchParam,
			@RequestParam(value = "sort") String sortBy) {
		List<Country> list = countryService.findCountry(pageNo, pageSize, searchParam, sortBy);
		return ResponseEntity.ok(list);
	}
}
