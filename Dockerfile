# Используем официальный образ OpenJDK 17
FROM eclipse-temurin:17-jdk-jammy

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем JAR-артефакт из target/ в контейнер
COPY target/*.jar app.jar

# Открываем порт, который использует приложение (замените 8080 на нужный)
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
