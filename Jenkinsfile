pipeline {
    agent any

    parameters {
        choice(name: 'ENVIRONMENT', choices: ['test', 'preprod'], description: 'Select the Environment')
        string(name: 'TAGS', defaultValue: '@api', description: 'Cucumber tags to execute')
    }

    stages {
        stage('Checkout') {
            steps {
                // Initial stage to pull the latest code from GitHub
                git branch: 'development', url: 'https://github.com/patricialeija85-byte/wk_automation'
            }
        }

        stage('Compile') {
            steps {
                // Separating the compilation to ensure dependencies are resolved before testing
                bat "mvn clean compile -DskipTests"
            }
        }

        stage('Execute Tests') {
            steps {
                script {
                    // Running the actual test suite using your dynamic parameters
                    bat "mvn test -Denv=${params.ENVIRONMENT} '-Dcucumber.filter.tags=${params.TAGS}'"
                }
            }
        }

        stage('Reports') {
            steps {
                // This stage will now appear in the Stage View once artifacts are archived
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/ExtentReports/*.html', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            // Clean up or notifications can go here
            echo 'Pipeline execution finished.'
        }
    }
}
