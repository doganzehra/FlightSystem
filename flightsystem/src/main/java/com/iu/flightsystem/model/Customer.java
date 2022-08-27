package com.iu.flightsystem.model;

public class Customer {

	private Long CUSTOMER_ID;

	private String CUSTOMER_NAME;

	public Long getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}

	public String getCUSTOMER_NAME() {
		return CUSTOMER_NAME;
	}

	public void setCUSTOMER_ID(Long cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}

	public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
		CUSTOMER_NAME = cUSTOMER_NAME;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(Long cUSTOMER_ID, String cUSTOMER_NAME) {
		super();
		CUSTOMER_ID = cUSTOMER_ID;
		CUSTOMER_NAME = cUSTOMER_NAME;
	}

	public Customer(String cUSTOMER_NAME) {
		super();
		CUSTOMER_NAME = cUSTOMER_NAME;
	}

	@Override
	public String toString() {
		return "Customer [CUSTOMER_ID=" + CUSTOMER_ID + ", CUSTOMER_NAME=" + CUSTOMER_NAME + "]";
	}

}
