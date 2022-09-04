package com.iu.flightsystem.model.viewobject;

public class CustomerPlaneFlightVO {
	private Long CUSTOMER_ID;

	private String CUSTOMER_NAME;

	private Long PLANE_ID;

	private String PLANE_NAME;

	private String PLANE_BRAND;

	private String FLIGHT_DATE;

	private Long FLIGHT_PRICE;

	public void setFLIGHT_DATE(String fLIGHT_DATE) {
		FLIGHT_DATE = fLIGHT_DATE;
	}

	public void setFLIGHT_PRICE(Long fLIGHT_PRICE) {
		FLIGHT_PRICE = fLIGHT_PRICE;
	}

	public String getFLIGHT_DATE() {
		return FLIGHT_DATE;
	}

	public Long getFLIGHT_PRICE() {
		return FLIGHT_PRICE;
	}

	public Long getPLANE_ID() {
		return PLANE_ID;
	}

	public String getPLANE_NAME() {
		return PLANE_NAME;
	}

	public String getPLANE_BRAND() {
		return PLANE_BRAND;
	}

	public void setPLANE_ID(Long pLANE_ID) {
		PLANE_ID = pLANE_ID;
	}

	public void setPLANE_NAME(String pLANE_NAME) {
		PLANE_NAME = pLANE_NAME;
	}

	public void setPLANE_BRAND(String pLANE_BRAND) {
		PLANE_BRAND = pLANE_BRAND;
	}

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

}
