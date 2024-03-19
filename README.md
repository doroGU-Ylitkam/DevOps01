# ServiceForStudy01
Java приложение, возвращающее при GET запросе ответ в формате json
```shell
curl -X GET app:8080/status
{"status":"Hello world!"}
```

Пример запуска приложения:
```shell
java -jar ServiceForStudy01-0.0.1-SNAPSHOT.jar
```

Пример запуска теста:
```shell
mvn --batch-mode test
```

Настраиваемые параметры через application.properties
```properties
#Порт, по которому доступно приложение
app.port=8080
#url, по которому доступно приложение
app.url=status
```