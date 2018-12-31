# notes
Sprint boot sample project using REST, JPA and MySQL

To package :
$mvn package

To run the application

1. Start mysql using the docker image 

$docker run -dit --env-file ./envfile -p 3306:3306 --name notesmysql mysql

2. Run the notes app :
$java -jar target/notes-0.0.1-SNAPSHOT.jar

3. Access the app at http://localhost:8080/api/notes

4. To test, post a note using curl :


