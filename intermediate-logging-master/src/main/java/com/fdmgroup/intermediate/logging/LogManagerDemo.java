package com.fdmgroup.intermediate.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public class LogManagerDemo {

	/*
	 *****************************************
	 *--------------LOGMANAGER---------------*
	 *****************************************
	 */

	// To get a Logger object, use the LogManager.getLogger methods

	// The root logger can be obtained by calling LogManager.getRootLogger()
	private static Logger rootLogger = LogManager.getRootLogger();

	// All other Loggers will be gotten by name, as defined by the "name" attribute
	// in configuration
	private static Logger helloLogger = LogManager.getLogger("Hello");

	// The convention is to name your Logger the fully qualified class name
	private static Logger loggerByClassString = LogManager
			.getLogger("com.fdmgroup.intermediate.logging.LogManagerDemo");
	// Of course, you wouldn't type this out: you can use the getName() method on
	// the class object
	private static Logger loggerByClassName = LogManager.getLogger(LogManagerDemo.class.getName());

	// This is more commonly retrieved using the class object itself
	private static Logger loggerByClassObject = LogManager.getLogger(LogManagerDemo.class);

	// Or, even more simply, by calling the no-arg getLogger(), which defaults
	// to using this class's fully qualified class-name.
	private static final Logger logger = LogManager.getLogger();

	// Most loggers can be called "logger" or "log".

	public static void main(String[] args) {

		logger.info("The most common convention is to fetch an instance of logger from the fully qualified classname.");
		logger.info("Use the no-arg getter for convenience if that is the case.");
		logger.info(
				"It is common practice to save the class's logger object as a private static variable for ease of use.");
	}
}
