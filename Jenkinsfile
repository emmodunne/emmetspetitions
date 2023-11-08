pipeline {
    agent any

    environment {
        WAR_FILE_NAME = 'myapp.war'
        TOMCAT_MANAGER_URL = "http://YOUR_TOMCAT_MANAGER_USERNAME:YOUR_TOMCAT_MANAGER_PASSWORD@localhost:8080/manager/text"
    }

    stages {
        stage('Checkout') {
            steps {
                // Get code from GitHub test
                git 'https://github.com/emmodunne/emmetspetitions.git'
            }
        }

         stage('Build, Test & Package') {
            steps {
                // Build and Test steps (Replace with actual build and test commands)
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                input 'Wait for Approval to Deploy'
                sh 'docker build -f Dockerfile -t petitionsapp .'
                sh 'docker rm -f "petitionsappcontainer" || true'
                sh 'docker run --name "petitionsappcontainer" -p 9090:8080 --detach petitionsapp:latest'
            }
        }
    }

    post {
        always {
            // Clean up workspace
            cleanWs()
        }
    }
}
