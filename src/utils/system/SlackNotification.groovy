package utils.system;
import utils.constants.BuildConstants;
import utils.constants.Colors;

class SlackNotification implements INotification {
    private _caller;
    private _env;

    SlackNotification(caller) {
        this._env = caller.env;
        this._caller = caller;
    }

    String getColorCode() {
        switch(this._caller.currentBuild.result) {
            case BuildConstants.Status.SUCCESS:
                return Colors.GREEN;
            case BuildConstants.Status.FAILURE:
                return Colors.RED;
            default:
                return Colors.YELLOW;
        }
    }

    String getSummary() {
        return "${this._env.ENV_NAME} ${this._env.JOB_NAME} Job has ${this._caller.currentBuild.result} (${this._env.BUILD_URL})";
    }

    Boolean sendNotification() {
        try {
            this._caller.slackSend(color: this.getColorCode(), message: this.getSummary());
            return true;
        } catch (Exception e) {
            this._caller.echo "Slack Ext Error: Please refer below for actual build log";
            this._caller.echo "${this._env.BUILD_URL}";
            this._caller.echo "Logging Error: ${e.getMessage()}";
            return false;
        }
    }
}