pipeline {
    agent any

    /*
       Defines the tools configured in 'Manage Jenkins > Tools'.
       The names inside quotes MUST match the 'Name' field you gave them in Jenkins.
    */
    tools {
        jdk 'Azul-17'   // Replace with the name you gave to your Azul Zulu 17 installation
        maven 'Maven_Latest' // Replace with the name you gave to your Maven installation
    }

    parameters {
        // Allows you to choose between test.properties or preprod.properties
        choice(name: 'ENVIRONMENT', choices: ['test', 'preprod'], description: 'Select the Environment')

        // Allows you to filter tests (e.g., @api, @ui, @regression)
        string(name: 'TAGS', defaultValue: '@api', description: 'Cucumber tags to execute')
    }

    stages {
        stage('Checkout') {
            steps {
                // GitHub repository checkout
                git branch: 'development', url: 'https://github.com/patricialeija85-byte/wk_automation'
            }
        }

        stage('Compile') {
            steps {
                // Compiles the project without running tests to ensure dependencies are fine
                bat "mvn clean compile -DskipTests"
            }
        }

        stage('Execute Tests') {
            steps {
                script {
                    /*
                       Executes Maven with your parameters.
                       Using double quotes for PowerShell/CMD compatibility in Jenkins.
                    */
                    bat "mvn test -Denv=${params.ENVIRONMENT} \"-Dcucumber.filter.tags=${params.TAGS}\""
                }
            }
        }

        stage('Reports') {
            steps {
                // Publish JUnit results and archive the Extent Report
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/ExtentReports/*.html', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution finished.'
        }
        success {
            echo 'Tests passed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check the logs and Extent Report.'
        }
    }
}