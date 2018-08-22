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

    String getSubject() {
        return "${this._env.ENV_NAME} ${this._env.JOB_NAME} Job - ${this._caller.currentBuild.result}";
    }
    
    String getDetails() {
        return """<p>${this._caller.currentBuild.result} ${this._env.JOB_NAME} Job in the ${this._env.ENV_NAME} environment.</p>
        <p>You can refer the build status by using the build number - ${this._env.BUILD_NUMBER}.</p>
        <p>Further information check console output at <a href="${this._env.BUILD_URL}">${this._env.JOB_NAME} [${this._env.BUILD_NUMBER}]</a></p>""";
    }

    Boolean sendNotification() {
        try {
            def temp = this._emailAddresses.join(', ');
            this._caller.echo "Email ids - ${this._emailAddresses}";
            this._caller.echo "Email ids string - ${temp}";
            this._caller.emailext (
                subject: this.getSubject(),
                body: this.getDetails(),
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