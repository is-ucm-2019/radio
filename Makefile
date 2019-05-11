all: compile

compile:
	mvn compile

run: package
	java -jar target/code-1.0-SNAPSHOT.jar -r

gen: package
	java -jar target/code-1.0-SNAPSHOT.jar -g

package:
	mvn package

