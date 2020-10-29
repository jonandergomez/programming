# Simple version of an agenda without contacts

This example is focused in the class **Event** that will use objects of the class
**Timestamp** as attributes, which in turn will require objects of the class
**TimeDelta**.

* [**Timestamp**](src/Timestamp.java) is a class for representing a time instant as an absolute reference using year, month, day of month, hour, minutes and seconds.
* [**TimeDelta**](src/TimeDelta.java) is a class for representing a lapse of time; useful for representing the duration of an event.
* [**Event**](src/Event.java) is a class for representing and event as the ones we can have in our agendas, with starting and ending times, title or description and location. For simplifying this example will not include the list of expected attendants to an event.

In order to illustrate the use of packages, the above *data* classes will be included in a package named **etsinf.prg.agenda1**. The main program for testing these classes will be outside the package, hence you will have the opportunity of seeing an example of how to import a package not provided by the JDK.

Additionally, the package will be integrated in a JAR file, and it will be provided the example of how to run a Java program including packages available in JAR files.
