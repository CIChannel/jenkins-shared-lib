package utils.system;

class EmailNotification implements INotification {
    private _buildInfo;
    private _subject;
    private _details;
    private _buildUrl;

    EmailNotification(BuildInfo bi, String[] emailAddresses) {
        this._buildInfo = bi;
        this._subject = "${env.ENV_NAME} ${env.JOB_NAME} Job has ${bi.status}";
        this._summary = "${subject} (${env.BUILD_URL})";
        this._details = """<p>${bi.status} ${env.JOB_NAME} Job in the ${env.ENV_NAME} environemt.</p>
        <p>You can refer the build status by using the build number - ${env.BUILD_NUMBER}.</p>
        <p>Further information check console output at <a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""";
    }

    Boolean notify() {
        try {
            emailext (
                subject: subject,
                body: details,
                to: emailAddresses.join(", ")
            )
            return true;
        } catch (Exception e) {
            echo "Email Ext Error: Please refer below for actual build log";
            echo "Build Log Error: ${ex.getMessage()}";
            return false;
        }
    }
}