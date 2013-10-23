package com.runner;

public class TestNgParameters {

	private final static TestNgParameters instance = new TestNgParameters();

	public final static TestNgParameters getInstance() {
		return instance;
	}

	private String[] suites;

	private String[] groups;


	public void setSuites(String[] suites) {
		this.suites = suites;
	}

	public String[] getSuites() {
		return suites;
	}

	public void setGroups(String[] groups) {
		this.groups = groups;
	}

	public String[] getGroups() {
		return groups;
	}
}
