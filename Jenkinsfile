pipeline {
    agent {
        label 'Build_Node' // 새로 생성한 노드 사용
    }
    environment {
        DOCKER_IMAGE_NAME = 'boardAppEx'
        DOCKER_CONTAINER_NAME = 'boardAppEx'
        GIT_REPO = 'https://github.com/odddman44/boardAppEx.git'
        JAR_FILE = 'build/libs/Board-0.0.1-SNAPSHOT.jar' // Gradle로 빌드된 JAR 파일
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
                ./gradlew clean build
                '''
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh '''
                    docker build -t ${DOCKER_IMAGE_NAME} .
                    '''
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    sh '''
                    docker stop ${DOCKER_CONTAINER_NAME} || true
                    docker rm ${DOCKER_CONTAINER_NAME} || true
                    docker run -d --name ${DOCKER_CONTAINER_NAME} -p 18080:18080 ${DOCKER_IMAGE_NAME}
                    '''
                }
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
