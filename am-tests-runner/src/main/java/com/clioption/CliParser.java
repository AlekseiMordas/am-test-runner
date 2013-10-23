package com.clioption;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;


public class CliParser {
	private static Logger LOGGER = Logger.getLogger(CliParser.class);

	private static List<ICliOption> cmdLineOptions = new ArrayList<ICliOption>();

	private static CommandLineParser cmdLineParser = new GnuParser();

	public static List<ICliOption> getCmdLineOptions() {
		return cmdLineOptions;
	}

	public static void parseCmdLineArgs(String[] args) {
		Options opts = CliParser.customOptsToCmdLineOpts();
		try {
			CommandLine cmd = CliParser.getCmdLineParser().parse(opts, args);
			CliParser.parseCmdLine(cmd);

		} catch (ParseException ex) {
			LOGGER.info(ex.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.setWidth(128);
			formatter.printHelp(" ", opts);
		}
	}

	public static Options customOptsToCmdLineOpts() {
		Options options = new Options();
		for (ICliOption opt : getCmdLineOptions()) {
			options.addOption(opt.getOption());
		}
		return options;
	}

	public static void parseCmdLine(CommandLine cmd) {
		LOGGER.info("------------------------------------------------------------------------");
		LOGGER.info(" Parsing command line: \"" + cmd.toString() + "\"");
		LOGGER.info("------------------------------------------------------------------------");
		for (ICliOption opt : getCmdLineOptions()) {
			String longName = opt.getOption().getLongOpt();
			LOGGER.debug(String.format("   Parsing --%s option", longName));
			if (cmd.hasOption(longName)) {
				String[] values = cmd.getOptionValues(longName);
				if (values != null && values.length != 0) {
					for (String value : values) {
						LOGGER.info(String.format("   %s=%s", longName, value));
					}
				}
				opt.parse(values);
			} else if (opt.getDefaultValue() != null) {
				String[] values = opt.getDefaultValue();
				for (String value : values) {
					LOGGER.info(String.format("   %s=%s", longName, value));
				}
				opt.parse(opt.getDefaultValue());
			}
		}
		LOGGER.info("------------------------------------------------------------------------");
	}

	public static void setCmdLineParser(CommandLineParser cmdLineParser) {
		CliParser.cmdLineParser = cmdLineParser;
	}

	public static CommandLineParser getCmdLineParser() {
		return cmdLineParser;
	}
}
