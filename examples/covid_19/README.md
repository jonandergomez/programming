# Use of arrays to manage COVID-19 data from the world

This example is devoted to extensively work with arrays, and is focused to work with real data
collected every day since COVID-19 pandemics started.
One data set is provided by countries with different criteria, so we cannot expect the data quality is regular.
The other dataset is related to Spain, with detail at the level of provinces.


The code of this example only works with the data set containing data of most of the countries of the world.

[**Row**](src/Row.java)
that will use objects of the class
[**Timestamp**](src/Timestamp.java)
as attributes, and will use them to create objects
of the class
[**TimeDelta**](src/TimeDelta.java)
to compute the duration of an event.

## Data

[Here](scripts) you can find the script to [download data](scripts/download_data.sh) from public URLs.
This script downloads two CSV *(comma separated values)* files, both are updated daily, so that we 
recommend you to **avoid download them more than once per day**, otherwise your IP address will be banned.

Both the CSV files should be located in the folder [data](data) that is not provided in this repository.
You have to create the folder and run the script to  [download data](scripts/download_data.sh) inside the [data](data) folder.


### Data set with world data

The dataset downloaded from [https://covid.ourworldindata.org](https://covid.ourworldindata.org]) is a CSV file with data from
most of the countries in the world, were each line (or row) contains data corresponding to one single day, so that the data of a
particular country appears in multiple lines/rows of the CSV file.
Rows are not guaranteed to be in chronological order.

#### Columns (attributes) per row

The following table shows the column names and the data type.
Some numerical attributes should be integers by nature, but are stored in the CSV file with decimal notation.

There are many columns in many rows that are empty, that is, there is no data.
In the example code that attributes will be set to zero, this decision can distort some statistics,
like average and standard deviation computed for some subsets of the data.


| ----------------------- | ----------- |
| Column name             |  Data type  |
| ----------------------- | ----------- |
| iso\_code | string |
| continent | string |
| location | string |
| date | yyyy-mm-dd |
| total\_cases | float |
| new\_cases | float |
| new\_cases\_smoothed | float |
| total\_deaths | float |
| new\_deaths | float |
| new\_deaths\_smoothed | float |
| total\_cases\_per\_million | float |
| new\_cases\_per\_million | float |
| new\_cases\_smoothed\_per\_million | float |
| total\_deaths\_per\_million | float |
| new\_deaths\_per\_million | float |
| new\_deaths\_smoothed\_per\_million | float |
| reproduction\_rate | float |
| icu\_patients | float |
| icu\_patients\_per\_milli | float |on
| hosp\_patients | float |
| hosp\_patients\_per\_milli | float |on
| weekly\_icu\_admissions | float |
| weekly\_icu\_admissions\_per\_milli | float |on
| weekly\_hosp\_admissions | float |
| weekly\_hosp\_admissions\_per\_milli | float |on
| new\_tests | float |
| total\_tests | float |
| total\_tests\_per\_thous | float |and
| new\_tests\_per\_thousand | float |
| new\_tests\_smoothed | float |
| new\_tests\_smoothed\_per\_thous | float |and
| positive\_rate | float |
| tests\_per\_case | float |
| tests\_units | float |
| total\_vaccinations | float |
| total\_vaccinations\_per\_hundr | float |ed
| stringency\_index | float |
| population | float |
| population\_density | float |
| median\_age | float |
| aged\_65\_older | float |
| aged\_70\_older | float |
| gdp\_per\_capita | float |
| extreme\_poverty | float |
| cardiovasc\_death\_rate | float |
| diabetes\_prevalence | float |
| female\_smokers | float |
| male\_smokers | float |
| handwashing\_facilities | float |
| hospital\_beds\_per\_thousand | float |
| life\_expectancy | float |
| human\_development\_index | float |
| ----------------------- | ----------- |



### Data set with Spanish data

| ----------------------- | ----------- |
| Column name             |  Data type  |
| ----------------------- | ----------- |
| provincia\_iso | string |
| fecha | yyyy-mm-dd |
| num\_casos | integer |
| num\_casos\_prueba\_pcr | integer |
| num\_casos\_prueba\_test\_ac | integer |
| num\_casos\_prueba\_otras | integer |
| num\_casos\_prueba\_desconocida | integer |
| ----------------------- | ----------- |



## Implementation details



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

Scripts to compile and run examples, and to generate the documentation and clean all the stuff, can be found [here](scripts).
