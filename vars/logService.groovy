#!/usr/bin/groovy
import java.util.logging.Logger;

Logger logger = Logger.getLogger("");

void info(msg) {
    logger.info("\nINFO: ${msg}\n");
}

void warning(warning, ex) {
    logger.info("\nWARNING: ${warning}\n");
    logger.info("\nEXCEPTION: ${exception.getMessage()}\n");
}

void error(msg, exception, isStackTrace = false) {
    logger.error("\nERROR: ${msg}\n");
    logger.error("\nEXCEPTION: ${exception.getMessage()}\n");
    if (isStackTrace) {
        logger.error("\nSTACKTRCE: ${exception.printStackTrace()}\n");
    }
}