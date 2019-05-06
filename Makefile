all: compile

compile:
	mvn compile

run:
	mvn package
	java -jar target/code-1.0-SNAPSHOT.jar

