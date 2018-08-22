import java.util.logging.Logger;

Logger logger = Logger.getLogger("");

def info(msg) {
    logger.info("INFO: ${msg}");
}

def warning(warning, ex) {
    logger.info("WARNING: ${warning}");
    logger.info("EXCEPTION: ${exception.getMessage()}");
}

def error(msg, exception, isStackTrace = false) {
    logger.error("ERROR: ${msg}");
    logger.error("EXCEPTION: ${exception.getMessage()}");
    if (isStackTrace) {
        logger.error("STACKTRCE: ${exception.printStackTrace()}");
    }
}