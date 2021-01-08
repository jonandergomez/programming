import etsinf.prg.covid19.*;

/**
  * Class for testing the different functionalities provided by the classes
  * included in the package <code>agenda1</code>.
  */
public class TestingCovid19
{
    public static void main(String [] args)
    {
        Timestamp now = new Timestamp();
        System.out.println();
        System.out.println("Current time is " + now);
        System.out.println();

        Dataset dataset = new Dataset("data/owid-covid-data.csv");

        System.out.println("Loaded " + dataset.getSize() + " days.");

        System.out.println();

        Dataset spain = dataset.filterByCountry("ESP");
        Dataset france = dataset.filterByCountry("FRA");
        Dataset germany = dataset.filterByCountry("DEU");

        System.out.println("Spanish dataset contains " + spain.getSize() + " days.");
        System.out.println("French  dataset contains " + france.getSize() + " days.");
        System.out.println("German  dataset contains " + germany.getSize() + " days.");

        System.out.println();

        Day d = dataset.maxCases();
        System.out.println("Day with most cases.: " + d);
        d = dataset.maxDeaths();
        System.out.println("Day with most deaths: " + d);

        System.out.println();

        d = spain.maxCases();
        System.out.println("Day with most cases  in Spain: " + d);
        d = spain.maxDeaths();
        System.out.println("Day with most deaths in Spain: " + d);

        System.out.println();

        d = france.maxCases();
        System.out.println("Day with most cases  in France: " + d);
        d = france.maxDeaths();
        System.out.println("Day with most deaths in France: " + d);

        System.out.println();

        d = germany.maxCases();
        System.out.println("Day with most cases  in Germany: " + d);
        d = germany.maxDeaths();
        System.out.println("Day with most deaths in Germany: " + d);
    }
}
