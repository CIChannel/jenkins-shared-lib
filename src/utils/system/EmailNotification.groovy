package utils.system;

class EmailNotification implements INotification {
    private _caller;
    private _env;
    private _emailAddresses;

    EmailNotification(caller, String[] emailAddresses) {
        this._emailAddresses = emailAddresses;
        this._env = caller.env;
        this._caller = caller;
    }

    String getSubject() {
        return "${this._env.ENV_NAME} ${this._env.JOB_NAME} Job - ${this._caller.currentBuild.result}";
    }
    
    String getDetails() {
        return """<p>${this._caller.currentBuild.result} ${this._env.JOB_NAME} Job in the ${this._env.ENV_NAME} environemt.</p>
        <p>You can refer the build status by using the build number - ${this._env.BUILD_NUMBER}.</p>
        <p>Further information check console output at <a href="${this._env.BUILD_URL}">${this._env.JOB_NAME} [${this._env.BUILD_NUMBER}]</a></p>""";
    }

    Boolean sendNotification() {
        try {
            emailext (
                subject: this.getSubject(),
                body: this.getDetails(),
                to: this._emailAddresses.join(", ")
            )
            return true;
        } catch (Exception e) {
            println("Email Ext Error: Please refer below for actual build log");
            println("${this._env.BUILD_URL}");
            println("Logging Error: ${ex.getMessage()}");
            return false;
        }
    }
}