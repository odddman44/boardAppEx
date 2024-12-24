pipeline {
    agent {
        label 'Build_Node'
    }
    environment {
        DOCKER_IMAGE_NAME = 'board_app'
        DOCKER_CONTAINER_NAME = 'board_app'
        GIT_REPO = 'https://github.com/odddman44/boardAppEx.git'
        JAR_FILE = 'build/libs/Board-0.0.1-SNAPSHOT.jar'
    }
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'master', url: "${GIT_REPO}"
            }
        }
        stage('Build with Gradle') {
            steps {
                sh '''
                chmod +x ./gradlew
                ./gradlew clean build -Dspring.profiles.active=jenkins
                '''
            }
        }
        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t ${DOCKER_IMAGE_NAME} .
                '''
            }
        }
        stage('Run with Docker Compose') {
            steps {
                sh '''
                docker-compose -f /home/oddd/app/board/docker-compose.yml up -d
                '''
            }
        }
    }
    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}