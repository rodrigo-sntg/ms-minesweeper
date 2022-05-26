/opt/maven/apache-maven-3.6.3/bin/mvn clean package
/opt/jdk-11.0.9/bin/java -jar target/minesweeper-1.0.1-SNAPSHOT.jar
# /opt/jdk-11.0.9/bin/java -jar target/quarkus-app/quarkus-run.jar

sudo chown -R $USER pgdata
docker build -f src/main/docker/Dockerfile.jvm -t rodrigosntg/minesweeper .
docker push rodrigosntg/minesweeper

docker-compose up --build