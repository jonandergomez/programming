package etsinf.prg.covid19;

import java.util.*;
import java.io.*;

/**
 * Class designed to contain the Covid-19 data loaded from a CSV file.
 */
public class Dataset
{
    /** Current size of the dataset: the number of valid elements in the array <code>data</code>. */
    private int     size;
    /** Array containing all the data samples loaded from the CSV file. */
    private Day []  data;

    /**
      * No default constructor allowed, so it is private.
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
        this();

        Scanner csv = null;

        try {
            csv = new Scanner(new File(filename)).useLocale(Locale.US);

            while (csv.hasNext()) {
                String line = csv.nextLine().trim();

                if (! line.startsWith("iso_code")) {

                    try {
                        this.add(new Day(line));
                    }
                    catch( Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
        finally {
            try {
                if (csv != null) csv.close();
            }
            catch(Exception e)
            {
                e.printStackTrace(System.err);
                System.exit(-1);
            }
        }
    }
    private void add(Day d)
    {
        if (size >= data.length) growData();

        data[size++] = d;
    }
    private void growData()
    {
        // create a new array with more capacity
        Day [] newData = new Day [data.length + 1000];

        // copy the elements of the old array to the beginning of the new one
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
        return false;
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
        return "";
    }

    /**
     *
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
     *
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
     *
     */
    public Dataset filterByCountry(String countryIsoCode)
    {
        Dataset filtered = new Dataset();

        for (int i = 0; i < size; ++i)
            if (data[i].get_iso_code().equals(countryIsoCode))
                filtered.add(data[i]);

        return filtered;
    }
}
