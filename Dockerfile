# Используем базовый образ с Java (только для запуска JAR)
FROM eclipse-temurin:17-jre-alpine

# Копируем JAR-артефакт из target/ в образ
COPY target/*.jar /app/app.jar

# Порт, который будет слушать приложение
EXPOSE 8080

# Команда для запуска
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
