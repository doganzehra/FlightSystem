package com.iu.flightsystem.model;

public class Group {

	private Long GROUP_ID;

	private Long GROUP_NO;

	public Long getGROUP_ID() {
		return GROUP_ID;
	}

	public Long getGROUP_NO() {
		return GROUP_NO;
	}

	public void setGROUP_ID(Long gROUP_ID) {
		GROUP_ID = gROUP_ID;
	}

	public void setGROUP_NO(Long gROUP_NO) {
		GROUP_NO = gROUP_NO;
	}

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public Group(Long gROUP_ID, Long gROUP_NO) {
		super();
		GROUP_ID = gROUP_ID;
		GROUP_NO = gROUP_NO;
	}

	public Group(Long gROUP_NO) {
		super();
		GROUP_NO = gROUP_NO;
	}

	@Override
	public String toString() {
		return "Group [GROUP_ID=" + GROUP_ID + ", GROUP_NO=" + GROUP_NO + "]";
	}

}
