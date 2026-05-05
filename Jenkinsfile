pipeline {
    agent any

    parameters {
        // Allows you to choose between test.properties or preprod.properties
        choice(name: 'ENVIRONMENT', choices: ['test', 'preprod'], description: 'Select the Environment')

        // Allows you to filter tests (e.g., @api, @ui, @regression)
        string(name: 'TAGS', defaultValue: '@api', description: 'Cucumber tags to execute')
    }

    stages {
        stage('Checkout') {
            steps {
                // Get code from your repository
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    // Executes Maven with dynamic environment and tags
                    // -Denv: passed to ConfigReader.java
                    // -Dcucumber.filter.tags: passed to Cucumber Runner
                    sh "mvn clean test -Denv=${params.ENVIRONMENT} -Dcucumber.filter.tags='${params.TAGS}'"
                }
            }
        }
    }

    post {
        always {
            // Archive Cucumber or JUnit reports if generated
            junit '**/target/surefire-reports/*.xml'
        }
    }
}