# Medium-level version of an agenda without contacts

This example is focused on the classes
[**Agenda**](src/Agenda.java)
and
[**Contact**](src/Contact.java)

In a previous [**introductory-level version**](../agenda1/) this example focused on class
[**Event**](src/Event.java)
that uses objects of the class [**Timestamp**](src/Timestamp.java) as attributes,
and uses objects of the class [**TimeDelta**](src/TimeDelta.java) to compute the duration
of an event from two timestamps.


* [**Timestamp**](src/Timestamp.java) is a class for representing a time instant as an absolute reference using year, month, day of month, hour, minutes and seconds.
* [**TimeDelta**](src/TimeDelta.java) is a class for representing a lapse of time; useful for representing the duration of an event.
* [**Event**](src/Event.java) is a class for representing an event as the ones we can have in our agendas, with starting and ending times, title or description and location. For simplifying this example will not include the list of expected attendants to an event.
* [**Contact**](src/Contact.java) is a class for representing a single contact we can have in our agendas, with the common attributes we need for each person we are in touch.
* [**Agenda**](src/Contact.java) is a class for representing an agenda with contacts and events.

In order to illustrate the use of packages, the above ***data*** classes will be included in a package named **etsinf.prg.agenda3**.
The main program for testing these classes will be outside the package, in the class
[**TestingAgenda3**](test_src/TestingAgenda3.java),
hence you will have the opportunity of seeing an example of how to import a package not provided by the JDK.

Additionally, the package will be integrated in a JAR file, and it will be provided the example of how
to run a Java program including packages available in JAR files.

The scripts to compile and run the example illustrate how
to create a JAR file and how to reference JAR files to
compile classes which use the package, and how to invoke
the JVM to use packages stored in JAR files.

Scripts to compile and run examples, and to generate
the documentation and clean all the stuff,
can be found [here](scripts).
