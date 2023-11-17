pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Get code from GitHub
                git 'https://github.com/emmodunne/emmetspetitions.git'
            }
        }

         stage('Build, Test & Package') {
            steps {
                // Build, Test and Package steps
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
