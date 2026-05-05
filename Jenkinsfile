pipeline {
    agent any

    /*
       Global Tool Configuration:
       Ensures the pipeline uses your specific Azul Zulu 17 and Maven installations.
    */
    tools {
        jdk 'Azul-17'   // Must match the Name in 'Manage Jenkins > Tools'
        maven 'Maven_Latest' // Must match the Name in 'Manage Jenkins > Tools'
    }

    parameters {
        // Dynamic environment selection
        choice(name: 'ENVIRONMENT', choices: ['test', 'preprod'], description: 'Select the target environment')

        // Dynamic Cucumber tag filtering
        string(name: 'TAGS', defaultValue: '@api', description: 'Enter Cucumber tags to execute (e.g., @api, @regression)')
    }

    stages {
        stage('Checkout') {
            steps {
                // Pulling latest code from your repository
                git branch: 'development', url: 'https://github.com/patricialeija85-byte/wk_automation'
            }
        }

        stage('Compile') {
            steps {
                // Fast-fail if the code has compilation errors
                bat "mvn clean compile -DskipTests"
            }
        }

        stage('Execute Tests') {
            steps {
                script {
                    /*
                       Running Maven tests with parameters.
                       Escaped double quotes are used for Windows CMD compatibility.
                    */
                    bat "mvn test -Denv=${params.ENVIRONMENT} \"-Dcucumber.filter.tags=${params.TAGS}\""
                }
            }
        }
    }

    /*
       Post-execution actions:
       This block runs regardless of whether the stages succeeded or failed.
    */
    post {
        always {
            script {
                // 1. Process JUnit XML results for the 'Tests' trend chart.
                //junit '**/target/surefire-reports/*.xml'

                // 2. Publish the Extent Report to the side menu.
                publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/ExtentReports',
                        reportFiles: 'SparkReport.html',
                        reportName: 'Extent Report'
                ])

                // 3. Backup the report as a build artifact.
                archiveArtifacts artifacts: 'target/ExtentReports/*.html', allowEmptyArchive: true
            }
            echo 'Pipeline execution complete. Review the Extent Report for details.'
        }
        success {
            echo 'Build Successful: All Wolters Kluwer tests passed!'
        }
        failure {
            echo 'Build Failed: One or more tests failed. Please check the Extent Report.'
        }
    }
}