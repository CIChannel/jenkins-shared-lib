#!/usr/bin/groovy
import java.util.logging.Logger;

void info(caller, msg) {
    echo "\nINFO: ${msg}\n";
}

void warning(caller, warning, ex) {
    echo "\nWARNING: ${warning}\n";
    echo "\nEXCEPTION: ${exception.getMessage()}\n";
}

void error(msg, exception, isStackTrace = false) {
    echo "\nERROR: ${msg}\n";
    echo "\nEXCEPTION: ${exception.getMessage()}\n";
    if (isStackTrace) {
        echo "\nSTACKTRACE: ${exception.getStackTrace()}\n";
    }
}