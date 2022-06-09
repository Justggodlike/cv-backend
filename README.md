###Mongo setup:

Get Mongo: https://www.mongodb.com/try/download/community  

###Mongo start up

Cmd (admin) - set mongo data path: mongod -dbpath [path]  
*e.g. on my local machine:*  
cd C:\Program Files\MongoDB\Server\5.0\bin\data  
mongod -dbpath .  
  
Cmd (admin) - start mongo server: [path]/mongo.exe  
*e.g. on my local machine:*  
cd C:\Program Files\MongoDB\Server\5.0\bin  
mongo.exe  

###Start application server (8080)  

Build the app from project root folder:  
./gradlew build  
  
Start the app using gradle wrapper from project root folder:  
./gradlew bootRun  
  
app host: http://localhost:8080/  
app Swagger: http://localhost:8080/api/swagger-ui/