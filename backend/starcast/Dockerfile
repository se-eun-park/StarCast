# 1. Java 기반의 JDK 이미지를 사용 (Slim 버전으로 시작하는 것이 좋음)
FROM openjdk:21-jdk-slim

# 2. 애플리케이션 파일을 저장할 디렉토리 설정
WORKDIR /app

# 3. Gradle에서 생성된 jar 파일을 컨테이너로 복사
# COPY build/libs/*.jar app.jar
COPY build/libs/*.jar /app/

# 4. 애플리케이션을 실행하기 위한 포트 열기 (Spring Boot의 기본 포트는 8080)
EXPOSE 8000

# 5. 애플리케이션을 실행 (환경에 따라 JVM 옵션을 추가 가능)
# ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
