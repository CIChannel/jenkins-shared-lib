package utils.system;

class EmailNotification implements INotification {
    private _caller;
    private _env;
    private _emailAddresses;

    EmailNotification(caller, emailAddresses) {
        this._emailAddresses = emailAddresses;
        this._env = caller.env;
        this._caller = caller;
    }

    String getSubject(failingStep) {
        return "${this._env.ENV_NAME} - ${this._env.JOB_NAME} Job is ${this._caller.currentBuild.result} At Step ${failingStep}";
    }
    
    String getDetails(failingStep) {
        return """<p>${this._caller.currentBuild.result} <a href="${this._env.JOB_DISPLAY_URL}">${this._env.JOB_NAME}</a> Job in the ${this._env.ENV_NAME} environment.</p>
        <p>You can refer the build status by using the build number - ${this._env.BUILD_NUMBER}.</p>
        <p>Failure Step - ${failingStep}.</p>
        <p>Further information check console output at <a href="${this._env.RUN_DISPLAY_URL}">${this._env.JOB_NAME} [${this._env.BUILD_NUMBER}]</a></p>
        <p>Build <a href="${this._env.RUN_CHANGES_DISPLAY_URL}">Changes</a></p></p>""";
    }

    Boolean sendNotification(failingStep = null) {
        try {
            def temp = this._emailAddresses.join(', ');
            this._caller.echo "Email ids - ${this._emailAddresses}";
            this._caller.echo "Email ids string - ${temp}";
            this._caller.emailext (
                subject: this.getSubject(failingStep),
                body: this.getDetails(failingStep),
                to: this._emailAddresses.join(", ")
            )
            return true;
        } catch (Exception e) {
            this._caller.echo "Email Ext Error: Please refer below for actual build log";
            this._caller.echo "${this._env.BUILD_URL}";
            this._caller.echo "Logging Error: ${e.getMessage()}";
            return false;
        }
    }
}