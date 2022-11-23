package com.country.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.country.entity.Country;
import com.country.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> findCountry(int pageNo, int pageSize, String searchParam, String sortBy) {

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<Country> pagedResult = countryRepository.findAll(pageable);

		if (pagedResult.hasContent()) {
			List<Country> list = pagedResult.getContent();
			if (null != searchParam)
				return list.stream().filter(s -> s.getName().toLowerCase().contains(searchParam.toLowerCase()))
						.collect(Collectors.toList());
			else
				return list;

		} else {
			return new ArrayList<Country>();
		}
	}
}
