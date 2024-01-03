package com.practise.webservices.RESTfulWebservices.Filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		
		SomeBean someBean = new SomeBean("value1","value2", "value3");
		
		MappingJacksonValue mappingjacksononvalue = new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter) ;
		
		mappingjacksononvalue.setFilters(filters);
		
	
		return mappingjacksononvalue;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringlist() {
		
		List<SomeBean> asList = Arrays.asList(new SomeBean("value1","value2", "value3"));
		
		MappingJacksonValue mappingjacksonvalue2 = new MappingJacksonValue(asList);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		mappingjacksonvalue2.setFilters(filters);
		return mappingjacksonvalue2;
	}

	

	
}
