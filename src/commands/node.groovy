package commands;

class NodeJS implements Serializable {
    def cmds;
    
    NodeJS(cmds) {
        // TODO: Move this to parent class such that commands can be
        // serialized into other build environments too
        this.cmds = cmds;
    }

    Boolean build(String[] additionalSteps) {
        try {
            if (isUnix()) {
                this.cmds.sh """
                    SET NODE_ENV = ${env.ENV_NAME} &&
                    npm install &&
                    npm run build
                """;
                this.cmds.sh additionalSteps.join(" && ");
            } else {
                this.cmds.bat """
                    SET NODE_ENV = ${env.ENV_NAME} &&
                    npm install &&
                    npm run build
                """;
                this.cmds.bat additionalSteps.join(" && ");
            }
            return true;
        } catch (Exception e) {
            echo "Build Log Error: ${e.getMessage()}";
            return false;
        }
    }

    Boolean test(String[] additionalSteps) {
        try {
            if (isUnix()) {
                this.cmds.sh """
                    set CI=true && npm run test
                """;
                this.cmds.sh additionalSteps.join(" && ");
            } else {
                this.cmds.bat """
                    set CI=true && npm run test
                """;
                this.cmds.bat additionalSteps.join(" && ");
            }
            return true;
        } catch (Exception e) {
            echo "Build Log Error: ${e.getMessage()}";
            return false;
        }
    }

}