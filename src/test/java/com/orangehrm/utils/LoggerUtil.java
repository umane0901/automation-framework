package com.orangehrm.utils;

import org.apache.log4j.Logger;

public abstract class LoggerUtil {
	public static Logger log = Logger.getLogger("devpinoyLogger");

	public static void logMessage(String message) {
		log.info(message);
	}
	
	public static void logError(String message) {
		log.error(message);
	}
	
	public static void logDebug(String message) {
		log.debug(message);
	}
}
