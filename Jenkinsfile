// This file sets up the use of the Job DSL plugin to scan the repository for jobs to create/change/delete

pipeline {
    agent {
        kubernetes {}
    }
    stages {
        stage('Process Jobs') {
            steps {
                jobDsl targets: ['*.groovy'].join('\n'), // Expects a string with newlines separating each target. Allows multiple.
                        removedJobAction: 'IGNORE',
                        removedViewAction: 'IGNORE',
                        lookupStrategy: 'JENKINS_ROOT'
            }
        }
    }
}
