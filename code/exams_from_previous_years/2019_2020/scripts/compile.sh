#!/bin/bash -eu

# compiles the package agenda2
for file in TimeInstant.java PieceOfNews.java BulletinBoard.java Exercises2and3.java
do
    javac -cp classes -d classes src/${file}
done

# creates the JAR file with the contents of the package
jar_filename="lib/exam2.jar"
rm -f ${jar_filename}
jar cvf ${jar_filename} -C classes etsinf/prg/exam2/

# compiles the other source files for testing the classes of the package.
# the JAR file is used in the class path to indicate the compiler that
# classes of the package are inside the JAR file in this case.
for file in TestExam2.java
do
    javac -cp ${jar_filename} -d classes test_src/${file}
    # javac -cp classes -d classes test_src/${file} # alternative
done
