package com.runner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.uncommons.reportng.HTMLReporter;

import com.clioption.CliParser;
import com.clioption.TestNgGroupOption;
import com.clioption.TestNgSuiteOption;
import com.utils.StringUtils;

/**
 * @author Aleksei_Mordas
 *
 */
public class Runner {

	private TestNG testNG = new TestNG();

	private static final String REPORTNG_ESCAPE_PROP = "org.uncommons.reportng.escape-output";

	private static final String TEST_OUTPUT_FOLDER = "test-output";

	private static final Logger LOGGER = Logger.getLogger(Runner.class);

	@SuppressWarnings("rawtypes")
	private List<Class> listeners = new ArrayList<Class>();

	public Runner(String[] args) {

		addCommandLineOptions();
		CliParser.parseCmdLineArgs(args);

		listeners.add(HTMLReporter.class);
		configureLogger();

	}

	private void configureLogger() {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource("log4j.properties");

		Properties prop = new Properties();
		try {
			prop.load(url.openStream());
		} catch (IOException e) {
			LOGGER.error("Used default log4j settings");
			e.printStackTrace();
		}
		PropertyConfigurator.configure(prop);
	}

	@SuppressWarnings("rawtypes")
	public void setListeners(List<Class> listeners) {
		this.listeners = listeners;
	}

	public void addCommandLineOptions() {
		CliParser.getCmdLineOptions().add(new TestNgSuiteOption());
		CliParser.getCmdLineOptions().add(new TestNgGroupOption());
	}

	public static void main(String[] args) {

		try {
			Runner tr = new Runner(args);

			tr.run();
		} catch (Exception e) {
			LOGGER.fatal("", e);
			throw new RuntimeException(e);

		}
	}

	public void run() {
		try {
			String[] suites = TestNgParameters.getInstance().getSuites();

			String[] groups = TestNgParameters.getInstance().getGroups();
			if (groups != null
					&& !groups[0].equals(TestNgGroupOption.DEFAULT_GROUP)) {

				testNG.setGroups(StringUtils.arrayToString(groups, ","));
			}
			testNG.setCommandLineSuite(new Parser(suites[0]).parseToList().get(
					0));
			testNG.setUseDefaultListeners(true);
			testNG.setListenerClasses(listeners);
			System.setProperty(REPORTNG_ESCAPE_PROP, "false");
			File dir = new File(TEST_OUTPUT_FOLDER);
			deleteDir(dir);
			dir.mkdirs();
			testNG.run();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {

		}
	}

	private boolean deleteDir(File dir) {
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDir(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (dir.delete());
	}

}
