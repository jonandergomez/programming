#!/usr/bin/env bash

javadoc -d docs \
        -private -author -version \
        -sourcepath src \
        -classpath classes \
        src/*.java
