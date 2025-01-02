/*
    Pipeline Example: Log Retention Mechanism
    -----------------------------------------
    This Jenkins pipeline demonstrates two approaches to enforce log retention:

    1. Dedicated Stage: 'Keep JobBuild'
       - Explicitly enables log retention ('currentBuild.keepLog = true') at the start of the pipeline execution.
       - Purpose:
           - Ensures logs are retained immediately when the pipeline starts.
           - Useful as a safeguard if earlier stages are required to keep logs for debugging.

    2. Post Block: Always Enforce Log Retention
       - Guarantees log retention at the end of the pipeline execution.
       - Purpose:
           - Ensures logs are kept regardless of the pipeline outcome:
               - Successful execution
               - Errors or failures in any stage
               - Manual pipeline abortion
           - Acts as a fallback mechanism if stages are skipped or the pipeline is restarted.

    Purpose of This Pipeline:
    - Serves as a reference for implementing log retention in Jenkins Declarative Pipelines.
    - Intended for educational and reference purposes, not for production use.
*/

pipeline {
    agent any
    stages {
        stage('Keep JobBuild') {
            steps {
                script {
                    // Enable log retention
                    currentBuild.keepLog = true
                }
            }
        }
    }
    post {
        always {
            script {
                // Enforce log retention at the end of the pipeline
                currentBuild.keepLog = true
                echo "Log retention enforced in post block."
            }
        }
    }
}
