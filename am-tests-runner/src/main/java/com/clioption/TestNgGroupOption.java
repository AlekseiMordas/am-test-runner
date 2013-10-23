package com.clioption;

import org.apache.commons.cli.Option;

import com.runner.TestNgParameters;

public class TestNgGroupOption implements ICliOption {
	public static final String DEFAULT_GROUP = "All";

	public String[] getDefaultValue() {

		return new String[] { DEFAULT_GROUP };
	}

	public Option getOption() {
		return new Option("group", "group", true,
				"Group of tests which will be running");
	}

	public void parse(String[] values) {
		TestNgParameters.getInstance().setGroups((values));
	}
}
