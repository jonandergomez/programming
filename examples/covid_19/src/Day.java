package etsinf.prg.covid19;

import java.util.Hashtable;

/**
 * Class designed to represent the data of one day per country.
 */
public class Day
{
    /*
        CSV header: list of columns names contained in the comma separated file:
        iso_code,continent,location,date,total_cases,new_cases,new_cases_smoothed,total_deaths,new_deaths,new_deaths_smoothed,total_cases_per_million,new_cases_per_million,new_cases_smoothed_per_million,total_deaths_per_million,new_deaths_per_million,new_deaths_smoothed_per_million,reproduction_rate,icu_patients,icu_patients_per_million,hosp_patients,hosp_patients_per_million,weekly_icu_admissions,weekly_icu_admissions_per_million,weekly_hosp_admissions,weekly_hosp_admissions_per_million,new_tests,total_tests,total_tests_per_thousand,new_tests_per_thousand,new_tests_smoothed,new_tests_smoothed_per_thousand,positive_rate,tests_per_case,tests_units,total_vaccinations,total_vaccinations_per_hundred,stringency_index,population,population_density,median_age,aged_65_older,aged_70_older,gdp_per_capita,extreme_poverty,cardiovasc_death_rate,diabetes_prevalence,female_smokers,male_smokers,handwashing_facilities,hospital_beds_per_thousand,life_expectancy,human_development_index

        A sample correspondinng to one day:
        ESP,Europe,Spain,2020-02-01,1.0,,,,,,0.021,,,,,,,,,,,,,,,,,,,,,,,,,,11.11,46754783.0,93.105,45.5,19.436,13.799,34272.36,1.0,99.403,7.17,27.4,31.4,,2.97,83.56,0.891

        We are only interested in the following columns that will be attributes
        of this class:
            iso_code
            date
            total_cases
            new_cases
            total_deaths
            new_deaths
            total_cases_per_million
            new_cases_per_million
            total_deaths_per_million
            new_deaths_per_million
    */
    /*
        we use a hash table to map column names with the column column index,
        please, do try to understand this becuase it will studied in EDA, second
        semester of second academic year.
    */
    private static Hashtable<String, Integer>  columnIndex = new Hashtable<String, Integer>();
    /*
        static initialization of the hash table; the code in the static block
        is executed when the class is loaded/imported, and cannot use instance
        variables because is similar to a static method; useful to initialize
        static variables of any class.
    */
    static {
        columnIndex.put("iso_code", 0);
        columnIndex.put("date", 3);
        columnIndex.put("total_cases", 4);
        columnIndex.put("new_cases", 5);
        columnIndex.put("total_deaths", 7);
        columnIndex.put("new_deaths", 8);
        columnIndex.put("total_cases_per_million", 10);
        columnIndex.put("new_cases_per_million", 11);
        columnIndex.put("total_deaths_per_million", 13);
        columnIndex.put("new_deaths_per_million", 14);
    }


    /** Date to which other data corresponds to. */
    private Timestamp date;
    /** Country identifier using the ISO code. */
    private String iso_code;
    /** Counter with total Covid-19 cases till the date. */
    private int total_cases;
    /** New cases counted in the current date. */
    private int new_cases;
    /** Counter with total deaths due to Covid-19 till the date. */
    private int total_deaths;
    /** New deaths counted in the current date. */
    private int new_deaths;
    /** Rate fo total Covid-19 cases per million till the date. */
    private float total_cases_per_million;
    /** Rate of new cases per million counted in the current date. */
    private float new_cases_per_million;
    /** Rate of accumulated deaths per million due to Covid-19 till the date. */
    private float total_deaths_per_million;
    /** Rate of new deaths per million counted in the current date. */
    private float new_deaths_per_million;

    /**
      * Private default constructor to avoid creating non-initialized
      * objects of this class, i.e. do not allow to create empty days.
      */
    private Day()
    {
    }
    /**
      * Generic constructor; creates an object from one string corresponding to
      * one line of the CSV file from which data is being loaded.
      *
      * @param line String with an entire line of the CSV file.
      */
    public Day(String line)
        throws Exception
    {
        /*
            using the comma as separator, the line is split into several strings
            returned in an array of strings, we set the limit to 52 columns
        */
        String [] columns = line.split(",", 52);

        /*
            in the case one line has an unexpected number of columns, it is
            considered an error and an exception is thrown
        */
        if (columns.length != 52)
            throw new Exception("Invalid line with " + columns.length
                              + " columns: " + line);

        // extracts the ISO code from the corresponding column
        this.iso_code = columns[columnIndex.get("iso_code")];

        // extracts the date from the corresponding column
        String s = columns[columnIndex.get("date")];
        /*
            converts each component to an integer according to
            the following format, the expected format:

            YYYY-MM-DD
            0123456789
        */
        int y = Integer.parseInt(s.substring(0, 4));
        int m = Integer.parseInt(s.substring(5, 7));
        int d = Integer.parseInt(s.substring(8, 10));

        /*
            creates a new object of the class Timestamp with the values of
            year, month and day
        */
        this.date = new Timestamp(y, m, d, 0, 0, 0);

        /*
            all the numeric columns corresponding to the eight columns we
            are interested in are extracted and converted following the same
            operations:
                1) get as an string the column according to its column index
                2) if the string is not empty, then convert it to a number
        */
        s = columns[columnIndex.get("total_cases")];
        if (s.length() > 0) this.total_cases  = (int)Float.parseFloat(s);
        s = columns[columnIndex.get("new_cases")];
        if (s.length() > 0) this.new_cases    = (int)Float.parseFloat(s);
        s = columns[columnIndex.get("total_deaths")];
        if (s.length() > 0) this.total_deaths = (int)Float.parseFloat(s);
        s = columns[columnIndex.get("new_deaths")];
        if (s.length() > 0) this.new_deaths   = (int)Float.parseFloat(s);

        s = columns[columnIndex.get("total_cases_per_million")];
        if (s.length() > 0) this.total_cases_per_million  = Float.parseFloat(s);
        s = columns[columnIndex.get("new_cases_per_million")];
        if (s.length() > 0) this.new_cases_per_million    = Float.parseFloat(s);
        s = columns[columnIndex.get("total_deaths_per_million")];
        if (s.length() > 0) this.total_deaths_per_million = Float.parseFloat(s);
        s = columns[columnIndex.get("new_deaths_per_million")];
        if (s.length() > 0) this.new_deaths_per_million   = Float.parseFloat(s);
    }

    /**
     * Returns the ISO code of the country.
     *
     * @return An string with the ISO code.
     */
    public String get_iso_code() { return iso_code; }

    /**
     * Returns the date.
     *
     * @return An object of the class <code>Timestamp</code>.
     */
    public Timestamp getDate() { return date.clone(); }

    /**
     * Returns the accumulated cases since the beginning till this day.
     *
     * @return An integer.
     */
    public int getTotalCases()  { return total_cases; }
    /**
     * Returns the number of cases counted this day.
     *
     * @return An integer.
     */
    public int getNewCases()    { return new_cases; }
    /**
     * Returns the accumulated deaths since the beginning till this day.
     *
     * @return An integer.
     */
    public int getTotalDeaths() { return total_deaths; }
    /**
     * Returns the number of deaths counted till this day.
     *
     * @return An integer.
     */
    public int getNewDeaths()   { return new_deaths; }

    /**
     * Returns the accumulated cases since the beginning till this day,
     * relative to one million people.
     *
     * @return An integer.
     */
    public float getTotalCasesPerMillion()  { return total_cases_per_million; }
    /**
     * Returns the number of cases counted this day relative to one million people.
     *
     * @return An integer.
     */
    public float getNewCasesPerMillion()    { return new_cases_per_million; }
    /**
     * Returns the accumulated deaths since the beginning till this day,
     * relative to one million people.
     *
     * @return An integer.
     */
    public float getTotalDeathsPerMillion() { return total_deaths_per_million; }
    /**
     * Returns the number of deaths counted this day relative to one million people.
     *
     * @return An integer.
     */
    public float getNewDeathsPerMillion()   { return new_deaths_per_million; }

    /** Compares two objects of this class to determine if they are equal;
      * only date and ISO code are used to consider whether two objects of
      * this class are equal.
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
        // first step is to check if the provided object can be compared with
        // the current one; the operator 'instanceof' is used
        if (o instanceof Day) {
            Day other = (Day)o; // with 'o' we cannot access attributes
            return this.iso_code.equals(other.iso_code)
                && this.date.equals(other.date);
        } else {
            // if the provided object is not an object of the class Timestamp
            // the comparison is not possible and we have to return false
            return false;
        }
    }

    /** Returns an object of the class <code>String</code> with the representation
      * of this object using a pure numerical style ready to be used for columns
      * in CSV files and databases.
      * CSV stands for comma separated values.
      *
      * @return A reference to a new created object of the class <code>String</code>.
      */
    @Override
    public String toString()
    {
        return iso_code + ';'
             + date.toString() + ';'
             + total_cases + ';'
             + new_cases + ';'
             + total_deaths + ';'
             + new_deaths + ';'
             + total_cases_per_million + ';'
             + new_cases_per_million + ';'
             + total_deaths_per_million + ';'
             + new_deaths_per_million;
    }

    /** Compares two objects of this class using the date in order to use this
      * compareTo() method to sort a list of objects of this class chronologically.
      *
      * @param other A reference to another object of the class <code>Day</code>.
      * @return A negative integer value when the current object is earlier than
      *         the other object given as parameter, zero when both timestamps are
      *         equal, and a positive value when the current object is later than
      *         the other object.
      */
    public int compareTo(Day other)
    {
        return this.date.compareTo(other.date);
    }

    /** Clones an object of this class.
      *
      * @return the reference to a new object of the class
      *         <code>Day</code> that is a
      *         copy of the current object.
      */
    @Override
    public Day clone()
    {
        Day d = new Day();

        d.iso_code = this.iso_code;
        d.date = this.date.clone();

        d.total_cases  = this.total_cases;
        d.new_cases    = this.new_cases;
        d.total_deaths = this.total_deaths;
        d.new_deaths   = this.new_deaths;

        d.total_cases_per_million  = this.total_cases_per_million;
        d.new_cases_per_million    = this.new_cases_per_million;
        d.total_deaths_per_million = this.total_deaths_per_million;
        d.new_deaths_per_million   = this.new_deaths_per_million;

        return d;
    }
}
