pipeline {
    agent any

    environment {
        IMAGE_NAME = 'blockchain-app'
        CONTAINER_PORT = '8080'
        HOST_PORT = '8080'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/muntinovaa/blockchain.git'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Java application with Maven...'
                sh 'mvn clean install'
            }
        }

        stage('Dockerize') {
            steps {
                echo 'Building Docker image...'
                script {
                    docker.build("${IMAGE_NAME}")
                }
            }
        }

        stage('Deploy') {
            steps {
                echo 'Running Docker container...'
                script {
                    docker.image("${IMAGE_NAME}").run("-p ${HOST_PORT}:${CONTAINER_PORT}")
                }
            }
        }
    }

    post {
        success {
            echo 'Build and deployment completed successfully.'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
