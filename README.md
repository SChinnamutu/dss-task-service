# dss-task-service

Tech Stack ::

Platform && Framework ::

Java8
Spring Boot
Jpa

Build Tool :: Maven

DB :: Postgresql

Editor :: Intellij



Running the application

mvn clean spring-boot:run

Create a jar file from the project
mvn clean package

Run from jar file, default profile
java -jar target\dss-ph-task-service-0.0.1-SNAPSHOT.jar

Collection Link:
https://www.postman.com/collections/7279e6b1e07c4e95c703

Add new record
curl -X POST \
  http://localhost:8086/dss/api/v1/task \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 33f4c0ef-0bec-4eae-a26e-dd24a11e2e20' \
  -H 'cache-control: no-cache' \
  -d '{
  "description": "Ronaldo is the best",
  "id": "a434e065-6bc6-490e-9e26-ea1b348b3891",
  "name": "Ronaldo",
  "status": true
}'



Listing all records
curl -X GET \
  http://localhost:8086/dss/api/v1/tasks \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 0402c92c-87a3-4af8-9434-b7a309869147' \
  -H 'cache-control: no-cache'

List particular record
curl -X GET \
  http://localhost:8086/dss/api/v1/task/a434e065-6bc6-490e-9e26-ea1b348b3812 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 36dfca0e-96f4-4a10-85fd-d53eef99c868' \
  -H 'cache-control: no-cache' 

Update
curl -X PUT \
  http://localhost:8086/dss/api/v1/task/a434e065-6bc6-490e-9e26-ea1b348b3812 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: fcd6b4ba-bbe9-4b7a-a19a-f879775bc8ee' \
  -H 'cache-control: no-cache' \
  -d '{
  "description": "Messi is the best",
  "name": "Messi",
  "status": true
}'

Delete a specific record
curl -X DELETE \
  http://localhost:8086/dss/api/v1/task/a434e065-6bc6-490e-9e26-ea1b348b3890 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: ddaada5c-eec3-4889-8bc0-831761d912dc' \
  -H 'cache-control: no-cache'
