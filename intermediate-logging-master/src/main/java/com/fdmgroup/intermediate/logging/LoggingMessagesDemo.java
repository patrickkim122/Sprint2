package com.fdmgroup.intermediate.logging;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingMessagesDemo {
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.info("If you are logging messages without parameters, just use plain text");
		logger.info("However, all logging methods are heavily overloaded "
				+ "to take in a multitude of additional parameters");
		logger.info("");
		
		logger.info("Use braces for parameters, as such: {}", "VALUE");
		logger.info("Messages can handle {} number of {}", "any", "parameters");
		logger.info("");
		
		logger.info("They can handle all types as well, using toString() for objects");
		logger.info("They box primitives and call their toString(), and use Arrays.toString() for arrays");
		logger.info("Primitives: int {}, double {}, boolean {}", 32, 76.54321, true);
		logger.info("Arrays: {}", new int[] { 5, 4, 3, 2, 1 });
		logger.info("Other objects: {}", Arrays.asList("Hello", "World"));
		logger.info("");
		
		logger.info("There are additional ways of formatting loggers, such as using format Strings like printf");
		logger.info("Log4j2 also provides a Message interface for more complex messages");
		logger.info("See the documentation for more ways, "
				+ "but strings and braces should be sufficient for most logging");

	}

}
