package etsinf.prg.covid19;

import java.util.*;
import java.io.*;

/**
 * Class designed to contain the Covid-19 data loaded from a CSV file.
 */
public class Dataset
{
    /** Current size of the dataset: the number of valid elements in
        the array <code>data</code>. */
    private int     size;
    /** Array containing all the data samples loaded from the CSV file. */
    private Day []  data;

    /**
      * Private default constructor to avoid creating objects with no data
      * from outside this class. However, this method is used in the filtering
      * methods that return a subset of the dataset stored in the current object.
      */
    private Dataset()
    {
        size = 0;
        data = new Day [1000];
    }
    /**
      * Generic constructor; creates an object from the given filename.
      *
      * @param line String the filename of the CSV file.
      */
    public Dataset(String filename)
    {
        // calls the private default constructor to initialize the array
        this();

        // declares and set to null an object of the class Scanner
        Scanner csv = null;
        try {
            /*
                initializes the object of the class Scanner with the
                file provided as parameter
            */
            csv = new Scanner(new File(filename)).useLocale(Locale.US);

            // read lines while more data is available in the file
            while (csv.hasNext()) {
                // read the line and removes training white spaces
                String line = csv.nextLine().trim();

                // if it is not the header of the CSV file
                if (! line.startsWith("iso_code")) {
                    try {
                        // creates a new object of the class Day and
                        // adds it to the data array of this object
                        this.add(new Day(line));
                    }
                    // catchs the exceptions the constructor of the class Day may throw
                    catch(Exception e) {
                        // print an error message without aborting the execution
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
        // catches the exception the constructor of the class Scanner may throw
        catch (FileNotFoundException e)
        {
            // prints an error message
            e.printStackTrace(System.err);
            // and aborts the execution of the program
            System.exit(-1);
        }
        finally {
            /*
                finally the object of the class Scanner must be closed,
                but we need to catch the exception the close() method may throw
            */
            try {
                if (csv != null) csv.close();
            }
            catch(Exception e)
            {
                // prints an error message
                e.printStackTrace(System.err);
                // and aborts the execution of the program
                System.exit(-1);
            }
        }
    }
    /**
     * Adds a new object of the class <code>Day</code> to the data array
     * of the current object. Calls the method <code>growData()</code>
     * whenever necessary to ensure there is enough place to add the new
     * element.
     *
     * @param d An object of the class <code>Day</code> to be inserted.
     */
    private void add(Day d)
    {
        // if no more free space in the data array, allocate more memory
        if (size >= data.length) growData();

        // store the day in the data array and increase the size of the dataset
        data[size++] = d;
    }
    /**
     * Increases the capacity of this object to store data by creating a new
     * array, copying the elements of the current data array into the new one
     * and then makes the new array as the current data array.
     */
    private void growData()
    {
        // creates a new array with more capacity
        Day [] newData = new Day [data.length + 1000];

        // copies the elements of the old array to the beginning of the new one
        for (int i = 0; i < data.length; ++i)
            newData[i] = data[i];

        // then the attribute 'data' points to the new array
        data = newData;
    }

    /** Returns the size of the dataset.
     * @return The number of elements stored in the dataset.
     */
    public int getSize() { return size; }


    /** Compares two objects of this class to determine if they are equal.
      * This method overrides the method equals of class <code>Object</code>
      * in order to adapt it to objects of the class <code>Day</code>.
      * To perform the correct overriding the parameter must be a reference to
      * objects of the class <code>Object</code>.
      *
      * @param o A reference to the object to be compared with the current one
      *          to check if both are equal.
      * @return <code>true</code> if <code>o</code> references to an object of this
      *         class and all the attributes match, <code>false</code> otherwise.
      */
    @Override
    public boolean equals(Object o)
    {
        boolean result = false;

        if (o instanceof Dataset) {
            Dataset other = (Dataset)o;

            result = this.size == other.size;

            for (int i = 0; i < this.size && result; ++i)
                result = this.data[i].equals(other.data[i]);
        }

        return result;
    }

    /** Returns an object of the class <code>String</code> with the reprsentation
      * this object using a pure numerical style ready to be used for columns
      * in CSV files and databases.
      * CSV stands for comma separated values.
      *
      * @return A reference to a new created object of the class <code>String</code>.
      */
    @Override
    public String toString()
    {
        return "It has no sense to return an string respresenting a list that"
             + " potentially can be extremely large!";
    }

    /**
     * Returns the day with the maximum number of new cases.
     * @return An object of the class <code>Day</code>.
     */
    public Day maxCases()
    {
        if (size == 0) return null;

        Day dayWithMaxCases = data[0];

        for (int i = 1; i < size; ++i)
            if (data[i].getNewCases() > dayWithMaxCases.getNewCases())
                dayWithMaxCases = data[i];

        return dayWithMaxCases;
    }
    /**
     * Returns the day with the maximum number of new deaths.
     * @return An object of the class <code>Day</code>.
     */
    public Day maxDeaths()
    {
        if (size == 0) return null;

        Day dayWithMaxDeaths = data[0];

        for (int i = 1; i < size; ++i)
            if (data[i].getNewDeaths() > dayWithMaxDeaths.getNewDeaths())
                dayWithMaxDeaths = data[i];

        return dayWithMaxDeaths;
    }

    /**
     * Returns a new object of the class <code>Dataset</code> with the
     *         elements that correspond to the country whose ISO code is
     *         provided as a reference.

     * @return An object of the class <code>Dataset</code>.
     */
    public Dataset filterByCountry(String countryIsoCode)
    {
        // creates a new empty dataset using the private default constructor
        Dataset filtered = new Dataset();

        // visit all the elements of the current dataset
        for (int i = 0; i < size; ++i)
            // add to the filtered dataset those elements that fulfil the condition
            if (data[i].get_iso_code().equals(countryIsoCode))
                filtered.add(data[i]);

        // return the new dataset containing a subset
        return filtered;
    }
}
