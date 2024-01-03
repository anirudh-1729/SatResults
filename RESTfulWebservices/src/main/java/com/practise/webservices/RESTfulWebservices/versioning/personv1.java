package com.practise.webservices.RESTfulWebservices.versioning;

public class personv1 {

	String name;

	public personv1(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "personv1 [name=" + name + "]";
	}
	
	
}
