package com.iu.flightsystem.model;

public class Cities {

	private Long CITY_ID;

	private String CITY_NAME;

	private Long CITY_GROUP_ID;

	public Long getCITY_ID() {
		return CITY_ID;
	}

	public String getCITY_NAME() {
		return CITY_NAME;
	}

	public Long getCITY_GROUP_ID() {
		return CITY_GROUP_ID;
	}

	public void setCITY_ID(Long cITY_ID) {
		CITY_ID = cITY_ID;
	}

	public void setCITY_NAME(String cITY_NAME) {
		CITY_NAME = cITY_NAME;
	}

	public void setCITY_GROUP_ID(Long cITY_GROUP_ID) {
		CITY_GROUP_ID = cITY_GROUP_ID;
	}

	public Cities() {
		// TODO Auto-generated constructor stub
	}

	public Cities(Long cITY_ID, String cITY_NAME, Long cITY_GROUP_ID) {
		super();
		CITY_ID = cITY_ID;
		CITY_NAME = cITY_NAME;
		CITY_GROUP_ID = cITY_GROUP_ID;
	}

	public Cities(String cITY_NAME, Long cITY_GROUP_ID) {
		super();
		CITY_NAME = cITY_NAME;
		CITY_GROUP_ID = cITY_GROUP_ID;
	}

	@Override
	public String toString() {
		return "Cities [CITY_ID=" + CITY_ID + ", CITY_NAME=" + CITY_NAME + ", CITY_GROUP_ID=" + CITY_GROUP_ID + "]";
	}

}
