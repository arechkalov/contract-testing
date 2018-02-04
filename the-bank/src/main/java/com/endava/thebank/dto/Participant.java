package com.endava.thebank.dto;

public class Participant {

	private String id;
	
	public Participant() {
	}

	public Participant(String name) {
		this.id = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
