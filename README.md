# Movie Storage Application
## Описание
REST приложение, которое позволяет отдавать фильмы по нескольким критериями, а также добавлять фильмы для хранения.

***
## Системные требования
Установленные:
- Java 11
- Apache Maven
***
## Установка
- Клонируйте репозиторий
  `git clone https://github.com/alela-mgn/movie-storage.git`
- выполните команду
  `mvn clean install`
***
## Запуск программы
- Выполните команду
  `java -jar target/MovieStorage-0.0.1-SNAPSHOT.jar`
***

Endpoints POST
---
Endpoint:
```
POST /movie-api/film/single/
```
Добавить фильм можно по следующей ссылке:
http://localhost:8080/movie-api/film/single/

Endpoint:
```
POST /movie-api/film/multiple/
```
Добавить фильмы можно по следующей ссылке:
http://localhost:8080/movie-api/film/multiple/
***
Endpoints GET
---
Endpoint:
```
GET /movie-api/film/search/name/{filmName}
```
Получить фильм по имени можно по следующей ссылке:
http://localhost:8080/movie-api/film/search/name/Форсаж

Endpoint:
```
GET /movie-api/film/search/type/{filmType}
```
Получить фильм по типу можно по следующей ссылке:
http://localhost:8080/movie-api/film/search/type/FULL_LENGTH

Endpoint:
```
GET /movie-api/film/search/date/{releaseDate}
```
Получить фильм по дате можно по следующей ссылке:
http://localhost:8080/movie-api/film/search/date/2012-05-03

Endpoint:
```
GET /movie-api/film/search/name/{filmName}?page=0
```
Получить фильм по имени для постраничного отображения можно по следующей ссылке:
http://localhost:8080/movie-api/film/search/name/Форсаж?page=0

Endpoint:
```
GET /movie-api/film/search/type/{filmType}?page=0
```
Получить фильм по типу для постраничного отображения можно по следующей ссылке:
http://localhost:8080/movie-api/film/search/type/FULL_LENGTH?page=0

Endpoint:
```
GET /movie-api/film/search/date/{releaseDate}?page=0
```
Получить фильм по дате для постраничного отображения можно по следующей ссылке:
http://localhost:8080/movie-api/film/search/date/2016-02-11?page=0
***
Documentation Swagger-UI
---
Перейти к документации приложения можно по следующей ссылке: http://localhost:8080/swagger-ui/index.html
***
Spring Actuator
---
Перейти к состоянию приложения можно по следующей ссылке: http://localhost:8080/actuator/health