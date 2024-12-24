pipeline {
    agent {
        label 'Build_Node' // 새로 생성한 노드 사용
    }
    environment {
        DOCKER_COMPOSE_PATH = '/home/oddd/app/board/docker-compose.yml'
        GIT_REPO = 'https://github.com/odddman44/boardAppEx.git'
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
        stage('Build and Run Docker Compose') {
            steps {
                script {
                    sh '''
                    docker-compose -f ${DOCKER_COMPOSE_PATH} down || true
                    docker-compose -f ${DOCKER_COMPOSE_PATH} up -d --build
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
            script {
                echo 'Pipeline failed. Checking Docker Compose logs...'
                sh '''
                docker-compose -f ${DOCKER_COMPOSE_PATH} logs
                '''
            }
        }
    }
}
