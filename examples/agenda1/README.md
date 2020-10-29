# Simple version of an agenda without contacts

This example is focused in the class
[**Event**](src/Event.java)
that will use objects of the class
[**Timestamp**](src/Timestamp.java)
as attributes, and will use them to create objects
of the class
[**TimeDelta**](src/TimeDelta.java)
to compute the duration of an event.


* [**Timestamp**](src/Timestamp.java) is a class for representing a time instant as an absolute reference using year, month, day of month, hour, minutes and seconds.
* [**TimeDelta**](src/TimeDelta.java) is a class for representing a lapse of time; useful for representing the duration of an event.
* [**Event**](src/Event.java) is a class for representing and event as the ones we can have in our agendas, with starting and ending times, title or description and location. For simplifying this example will not include the list of expected attendants to an event.

In order to illustrate the use of packages, the above ***data*** classes will be included in a package named **etsinf.prg.agenda1**. The main program for testing these classes will be outside the package,
in the class
[**TestingAgenda1**](test_src/TestingAgenda1.java),
hence you will have the opportunity of seeing an example of how to import a package not provided by the JDK.

Additionally, the package will be integrated in a JAR file, and it will be provided the example of how to run a Java program including packages available in JAR files.

The scripts to compile and run the example illustrate how
to create a JAR file and how to reference JAR files to
compile classes which use the package, and how to invoke
the JVM to use packages stored in JAR files.

Scripts to compile and run examples, and to generate
the documentation and clean all the stuff,
can be found [here](scripts).
