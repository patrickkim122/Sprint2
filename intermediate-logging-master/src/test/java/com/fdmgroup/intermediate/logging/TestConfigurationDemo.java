package com.fdmgroup.intermediate.logging;

import org.junit.Test;

public class TestConfigurationDemo {

	/*
	 * Because this is running as a test, log4j2-test.xml will be used instead of
	 * log4j2.xml. For that configuration, this means that trace-level events will
	 * be shown, and a temp file is created instead of the permanent log.
	 */

	@Test
	public void showTestConfiguration() {
		ConfigurationDemo.main(null);
	}
}
