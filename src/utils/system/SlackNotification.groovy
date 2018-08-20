package utils.system;
import utils.constants;

class SlackNotification implements INotification {
    private _buildInfo;

    SlackNotification(BuildInfo bi, String[] emailAddresses) {
        this._buildInfo = bi;
        this._summary = "${env.ENV_NAME} ${env.JOB_NAME} Job has ${bi.status} (${env.BUILD_URL})";
    }

    String getColorCode() {
        switch(this._buildInfo.status) {
            case BuildConstants.Status.SUCCESS:
                return Colors.Status.GREEN;
            case BuildConstants.Status.FAILURE:
                return Colors.Status.RED;
            default:
                return Colors.Status.YELLOW;
        }
    }

    Boolean notify() {
        try {
            slackSend (color: this.getColorCode(), message: summary);
            return true;
        } catch (Exception e) {
            echo "Slack Ext Error: Please refer below for actual build log";
            echo "Build Log Error: ${ex.getMessage()}";
            return false;
        }
    }
}