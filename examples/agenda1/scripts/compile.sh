#!/bin/bash -eu

# compiles the package agenda1
for file in Timestamp.java TimeDelta.java Event.java
do
    javac -cp classes -d classes src/${file}
done

# creates the JAR file with the contents of the package
jar_filename="lib/agenda1.jar"
rm -f ${jar_filename}
jar cvf ${jar_filename} -C classes etsinf/prg/agenda1/

# compiles the other source files for testing the classes of the package.
# the JAR file is used in the class path to indicate the compiler that
# classes of the package are inside the JAR file in this case.
for file in TestingAgenda1.java
do
    javac -cp ${jar_filename} -d classes test_src/${file}
    # javac -cp classes -d classes test_src/${file} # alternative
done
