# 1. OpenJDK를 베이스 이미지로 사용
FROM openjdk:11-jdk-slim

# 2. JAR 파일 복사
# Gradle 빌드 후 생성된 JAR 파일을 복사합니다.
# ARG JAR_FILE=build/libs/Board-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} /board.jar
COPY build/libs/*.jar /board.jar

# 3. 포트 설정 (Spring Boot 기본 포트 18080)
EXPOSE 18080

# 4. 애플리케이션 실행
#ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/board.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/board.jar"]