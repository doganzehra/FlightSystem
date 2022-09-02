package com.iu.flightsystem.model.viewobject;

public class CustomerFlightDateVO {
	
	private Long CUSTOMER_ID;
	
	private String CUSTOMER_NAME;
	
	private String FLIGHT_DATE;

	public Long getCUSTOMER_ID() {
		return CUSTOMER_ID;
	}

	public void setCUSTOMER_ID(Long cUSTOMER_ID) {
		CUSTOMER_ID = cUSTOMER_ID;
	}

	public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
		CUSTOMER_NAME = cUSTOMER_NAME;
	}
	public String getCUSTOMER_NAME() {
		return CUSTOMER_NAME;
	}
	public void setFLIGHT_DATE(String fLIGHT_DATE) {
		FLIGHT_DATE = fLIGHT_DATE;
	}
	public String getFLIGHT_DATE() {
		return FLIGHT_DATE;
	}
	
}
