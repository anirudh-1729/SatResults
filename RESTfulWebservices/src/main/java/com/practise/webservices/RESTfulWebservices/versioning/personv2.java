package com.practise.webservices.RESTfulWebservices.versioning;

public class personv2 {

	private Name name;

	public personv2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	@Override
	public String toString() {
		return "personv2 []";
	}
	
	
}
