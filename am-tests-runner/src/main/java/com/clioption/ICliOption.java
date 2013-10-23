package com.clioption;

import org.apache.commons.cli.Option;

public interface ICliOption {
	/*
	 * Parse command line argument specified to that option in command line.
	 */
	public void parse(String[] values);

	/*
	 * Get option .
	 */
	public Option getOption();

	/**
	 * 
	 * @return values for option by default. null if no default values.
	 */
	public String[] getDefaultValue();
}
