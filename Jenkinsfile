pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-org/selenium-docker-grid.git'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                script {
                    sh 'docker-compose up --build --abort-on-container-exit'
                }
            }
        }

        stage('Archive Report') {
            steps {
                archiveArtifacts artifacts: 'target/extent-report/**', fingerprint: true
            }
        }

        stage('Publish HTML Report') {
            steps {
                publishHTML([
                    reportDir: 'target/extent-report',
                    reportFiles: 'extent.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }

    post {
        always {
            sh 'docker-compose down -v || true'
        }
    }
}
