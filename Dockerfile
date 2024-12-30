# 1. OpenJDK를 베이스 이미지로 사용
FROM openjdk:11-jdk-slim

# Docker CLI 설치
USER root
RUN apt-get update && apt-get install -y docker.io && apt-get clean
# 기본 사용자로 복귀
USER jenkins

# 2. JAR 파일 복사
# Gradle 빌드 후 생성된 JAR 파일을 복사합니다.
ARG JAR_FILE=build/libs/Board-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /board.jar

# 3. 포트 설정 (Spring Boot 기본 포트 18080)
EXPOSE 18080

# 4. 애플리케이션 실행
#ENTRYPOINT ["java", "-jar", "/board.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/board.jar"]