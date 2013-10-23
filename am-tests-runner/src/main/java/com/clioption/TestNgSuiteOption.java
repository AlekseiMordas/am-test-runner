package com.clioption;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;

import com.runner.TestNgParameters;

public class TestNgSuiteOption implements ICliOption {

	private static final String DEFAULT_SUITE = "testng.xml";

	@Override
	public void parse(String[] values) {
		TestNgParameters.getInstance().setSuites(values);
	}

	@SuppressWarnings("static-access")
	@Override
	public Option getOption() {
		return OptionBuilder.withArgName("path_to_config").isRequired(false)
				.hasArgs().withLongOpt("testng")
				.withDescription("Path to TestNG config file").create("tng");
	}

	@Override
	public String[] getDefaultValue() {
		return new String[] { DEFAULT_SUITE };
	}

}
