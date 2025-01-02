pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                // From GitHub SCM
                git branch: 'master', url: 'https://github.com/odddman44/boardAppEx.git'
            }
        }
        stage('Build') {
            steps {
                // Gradle 빌드
                sh 'chmod +x ./gradlew'
                sh './gradlew clean build -x test'
            }
        }
        stage('Docker Build') {
            steps {
                // Docker 이미지 빌드
                sh '''
                export DOCKER_BUILDKIT=1
                docker build -t board-app-image .
                '''
            }
        }
        stage('Run Container') {
            steps {
                // Docker 컨테이너 실행
                sh '''
                docker stop board-app || true
                docker rm board-app || true
                docker run -d --name board-app -p 18080:18080 \
                    --network app_network \
                    board-app-image
                '''
            }
        }
    }
}
