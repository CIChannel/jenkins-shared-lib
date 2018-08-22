#!/usr/bin/groovy
import java.util.logging.Logger;

Logger logger = Logger.getLogger("");

void info(caller, msg) {
    caller.info("\nINFO: ${msg}\n");
}

void warning(caller, warning, ex) {
    caller.info("\nWARNING: ${warning}\n");
    caller.info("\nEXCEPTION: ${exception.getMessage()}\n");
}

void error(caller, msg, exception, isStackTrace = false) {
    if (caller) {
        caller.error("\nERROR: ${msg}\n");
        caller.error("\nEXCEPTION: ${exception.getMessage()}\n");
        if (isStackTrace) {
            caller.error("\nSTACKTRCE: ${exception.printStackTrace()}\n");
        }
    } else {
        echo "\nERROR: ${msg}\n";
        echo "\nEXCEPTION: ${exception.getMessage()}\n";
        if (isStackTrace) {
            echo "\nSTACKTRCE: ${exception.printStackTrace()}\n";
        }
    }
}