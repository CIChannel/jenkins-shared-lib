package utils.system;

class LoggingService implements Serializable {
    private _caller;
    private _logger;

    LoggingService(caller) {
        this._caller = caller;
        this._logger = caller.logger;
    }

    void info(caller, msg) {
        this._logger.info("\nINFO: ${msg}\n");
    }

    void warning(caller, warning, ex) {
        this._logger.info("\nWARNING: ${warning}\n");
        this._logger.info("\nEXCEPTION: ${exception.getMessage()}\n");
    }

    void error(caller, msg, exception, isStackTrace = false) {
        if (caller) {
            this._logger.error("\nERROR: ${msg}\n");
            this._logger.error("\nEXCEPTION: ${exception.getMessage()}\n");
            if (isStackTrace) {
                this._logger.error("\nSTACKTRCE: ${exception.printStackTrace()}\n");
            }
        } else {
            echo "\nERROR: ${msg}\n";
            echo "\nEXCEPTION: ${exception.getMessage()}\n";
            if (isStackTrace) {
                echo "\nSTACKTRCE: ${exception.printStackTrace()}\n";
            }
        }
    }
}