#!/bin/bash -eu

# compiles the package covid19
for file in src/Timestamp.java src/*.java 
do
    javac -cp classes -d classes ${file}
done

# creates the JAR file with the contents of the package
jar_filename="lib/covid19.jar"
rm -f ${jar_filename}
jar cvf ${jar_filename} -C classes etsinf/prg/covid19/

# compiles the other source files for testing the classes of the package.
# the JAR file is used in the class path to indicate the compiler that
# classes of the package are inside the JAR file in this case.
for file in test_src/TestingCovid19.java
do
    javac -cp ${jar_filename} -d classes ${file}
    # javac -cp classes -d classes test_src/${file} # alternative
done
