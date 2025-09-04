# Java 21 런타임 (안정)
FROM eclipse-temurin:21-jre

# 타임존(선택)
ENV TZ=Asia/Seoul
# 컨테이너 메모리 인지 (t3.micro 대비 안전한 기본값)
ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70"

WORKDIR /app
# 로컬에서 빌드한 JAR만 복사 (아래 1단계에서 빌드)
COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
