all: compile

compile:
	mvn compile

run: package
	java -jar target/is_ucm-1.0.jar -r

gen: package
	java -jar target/is_ucm-1.0.jar -g

package:
	mvn package

