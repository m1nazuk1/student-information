запуск

docker compose build
docker compose up -d

получение токена

curl -X POST http://localhost:9000/oauth/token   -d 'client_id=testclient&client_secret=testsecret&username=user&password=pass'

пример создания студента

curl -X PUT http://localhost:9000/api/students   -H 'Content-Type: application/json'   -H 'Authorization: Bearer <token>'   -d '{
    "lastName": "иванов",
    "firstName": "иван",
    "middleName": "иванович",
    "group": "110",
    "gpa": 4.5
  }'

пример изменения студента

curl -X POST http://localhost:9000/api/students/<id>   -H 'Content-Type: application/json'   -H 'Authorization: Bearer <token>'   -d '{
    "lastName": "петров",
    "firstName": "петр",
    "middleName": "петрович",
    "group": "112",
    "gpa": 4.2
  }'

пример получения списка студентов

curl -X GET http://localhost:9000/api/students   -H 'Authorization: Bearer <token>'

пример получения одного студента

curl -X GET http://localhost:9000/api/students/<id>   -H 'Authorization: Bearer <token>'

пример удаления студента

curl -X DELETE http://localhost:9000/api/students/<id>   -H 'Authorization: Bearer <token>'

