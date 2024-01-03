package com.practise.webservices.RESTfulWebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	@GetMapping("/v1/person")
	public personv1 getfirstpersonversion() {
		
		return new personv1("Anirudh Kumar");
	}
	
	@GetMapping("/v2/person")
	public personv2 getsecondpersonverson() {
		
		return new personv2(new Name("anirudh", "kumar"));
	}
}
