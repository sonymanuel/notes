#!/bin/bash -x

cd $(dirname $0)

#setup mysql env file
echo MYSQL_ROOT_PASSWORD=admin > envfile
echo MYSQL_USER=user >> envfile
echo MYSQL_PASSWORD=password >> envfile
echo MYSQL_DATABASE=notes_app >> envfile

exit 1;

#launch mysql docker container
docker run -dit --name notesmysql -p3306:3306 --env-file=./envfile mysql

sleep 180

#extract mysql ipaddr
ipaddr=`docker container inspect notesmysql -f '{{json .NetworkSettings.IPAddress}}' | xargs `

echo "mysql IP address = $ipaddr "

#launch notes docker image
docker run -dit --name notes -p 7080:8080 -e mysql_host=$ipaddr notes.img

sleep 30

#run the maven test
cd ..
mvn test -Dtest=com.sm.notes.test.NotesFunctionalTest -DnotesURL=http://localhost:7080/api/notes
exit_status=$?

docker stop notes notesmysql
docker rm notes notesmysql

exit $exit_status

