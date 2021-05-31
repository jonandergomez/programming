#!/usr/bin/env bash

# Example of running classes with the main method and using the classes of the
# package gathered in the JAR file that can be used as one of the paths the
# JVM can look for compiled classes.
java -cp lib/agenda2.jar:classes  TestingAgenda2
