Postal Item Tracker - REST API

Сборка осуществляется с помощью gradle
В качестве очереди используется Kafka
В качестве БД - postgresql

Для запуска в application.yml необходимо указать:
database.url
datasource.username
datasource.password

После первого запуска в таблице post_office будет создано несколько почтовых отделений для удобства тестирования

Для REST API доступен swagger по адресу
http://localhost:8080/swagger-ui/index.html
