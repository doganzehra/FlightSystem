package com.iu.flightsystem.model;

public class Plane {

	private Long PLANE_ID;

	private String PLANE_NAME;

	private String PLANE_BRAND;

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

	public Plane() {
		// TODO Auto-generated constructor stub
	}

	public Plane(Long pLANE_ID, String pLANE_NAME, String pLANE_BRAND) {
		super();
		PLANE_ID = pLANE_ID;
		PLANE_NAME = pLANE_NAME;
		PLANE_BRAND = pLANE_BRAND;
	}

	public Plane(String pLANE_NAME, String pLANE_BRAND) {
		super();
		PLANE_NAME = pLANE_NAME;
		PLANE_BRAND = pLANE_BRAND;
	}

	@Override
	public String toString() {
		return "Plane [PLANE_ID=" + PLANE_ID + ", PLANE_NAME=" + PLANE_NAME + ", PLANE_BRAND=" + PLANE_BRAND + "]";
	}

}
