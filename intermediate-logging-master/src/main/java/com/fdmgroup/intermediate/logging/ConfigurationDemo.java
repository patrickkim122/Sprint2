package com.fdmgroup.intermediate.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationDemo {
	private static final Logger logger = LogManager.getLogger("Hello");

	public static void main(String[] args) {
		/*
		 * Configuration in log4j2 is typically achieved via configuration files. This
		 * configuration can be done in a variety of file types: properties, XML, YAML,
		 * and JSON.
		 * 
		 * We will be using xml but the information for all configuration types is here:
		 * https://logging.apache.org/log4j/2.x/manual/configuration.html#
		 * Automatic_Configuration
		 * 
		 * In log4j2, configuration is automatically loaded for loggers as long as the
		 * config file is named log4j2. That means they will work as expected from the
		 * get-go. Change the level of the logger in log4j2.xml to see the results
		 * change.
		 * 
		 * Test logging files are used first in automatic configuration. The files
		 * should be named log4j2-test and placed in src/test/resources. When testing
		 * using Maven or Eclipse, these resources will automatically be deployed and
		 * used correctly from the get-go.
		 */

		logger.trace("Hello Trace");
		logger.debug("Hello Debug");
		logger.info("Hello Info");
		logger.warn("Hello Warn");
		logger.error("Hello Error");
		logger.fatal("Hello Fatal");

		/*
		 * While log4j2 will only log the messages at the right level by default,
		 * sometimes an expensive message must be constructed for that log. This can be
		 * explicitly checked with "is{Level}Enabled".
		 */
		if (logger.isWarnEnabled()) {
			logger.warn(expensiveOperation());
		}

		/*
		 * Java 8 improves the readability by using a lambda expression as a message
		 * supplier. Instead of the message always being created and passed to the
		 * method, this message will only be constructed if it can be logged by the
		 * given logger.
		 */
		logger.warn(() -> expensiveOperation());

		/*
		 * As an extra note, tracing is used often to track method entry and exit. There
		 * are convenience methods that log exactly that.
		 */
		tracedMethod();

	}

	private static String expensiveOperation() {
		try {
			Thread.sleep(2_000); // Expensively waits for 2 seconds
		} catch (InterruptedException e) {
		}
		return "Expensive message";
	}

	private static void tracedMethod() {
		logger.traceEntry();
		/*
		 * If the method has parameters, you can pass them all in using
		 * Logger.traceEntry(String format, Object... params) Note that earlier versions of log4j2 used
		 * entry() instead. This method is deprecated.
		 */

		logger.traceExit();
		/*
		 * If the method returns a value, exit with the result Logger.traceExit(R
		 * result) Note that earlier versions of log4j2 used exit() instead. This method
		 * is deprecated.
		 */
	}

}
