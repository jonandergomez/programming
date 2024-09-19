

all:
	javac -cp classes -d classes src/*.java
	#javac -cp . -d . src/*.java

clean:
	rm -rf classes/*
	rm -rf linear/*
	rm -rf docs/

doc:
	javadoc -d docs src/*.java


jar:
	jar cvf linear.jar linear src


test-stacks:
	java -cp . linear.TestStacks

test-queues:
	java -cp . linear.TestQueues

test-lists:
	java -cp . linear.TestLists

test-lists2:
	java -cp . linear.TestLists2
