
import java.util.*;
import java.io.*;

import etsinf.prg.exam2.*;


public class TestExam2
{
    private TestExam2() {} // To avoid create objects of this class.

    public static void main(String [] args)
    {
        BulletinBoard bb = new BulletinBoard();

        bb.add(new PieceOfNews(new TimeInstant(10, 30),
                               "https://www.nytimes.com/2021/01/19/science/astronomy-black-hole-abell.html?action=click&module=Editors%20Picks&pgtype=Homepage",
                               1,
                               PieceOfNews.TEXT));

        bb.add(new PieceOfNews(new TimeInstant(12, 00),
                               "https://www.nytimes.com/2021/01/19/science/astronomy-black-hole-abell.html?action=click&module=Editors%20Picks&pgtype=Homepage",
                               3,
                               PieceOfNews.TEXT));

        bb.add(new PieceOfNews(new TimeInstant(11, 15),
                               "https://www.nytimes.com/2021/01/19/science/nebra-sky-disk.html?action=click&block=more_in_recirc&impression_id=58e35ba3-5af3-11eb-aeec-9721b6f2e12f&index=0&pgtype=Article&region=footer",
                               1,
                               PieceOfNews.TEXT));

        bb.add(new PieceOfNews(new TimeInstant(13, 25),
                               "https://www.bbc.com/news/av/science-environment-55713364",
                               1,
                               PieceOfNews.VIDEO));


        String link = "https://www.bbc.com/news/av/science-environment-55713364";
        PieceOfNews p = bb.isPosted(link);
        if (p != null)
            System.out.println("Link is posted:\n" + p);
        else
            System.out.printf("Link %s is not posted.\n", link);

        link = "https://www.bbc.com/news/";
        p = bb.isPosted(link);
        if (p != null)
            System.out.println("Link is posted:\n" + p);
        else
            System.out.printf("Link %s is not posted.\n", link);

        PieceOfNews [] videos = bb.filterByType(PieceOfNews.VIDEO);
        PieceOfNews [] texts  = bb.filterByType(PieceOfNews.TEXT);
        PieceOfNews [] audios = bb.filterByType(PieceOfNews.AUDIO);
        if (videos.length > 0) {
            System.out.println("VIDEOS:");
            for (int i = 0; i < videos.length; ++i) System.out.println(videos[i]);
        } else {
            System.out.println("There are no videos in the bulletin board!");
        }
        if (texts.length > 0) {
            System.out.println("TEXTS:");
            for (int i = 0; i < texts.length; ++i) System.out.println(texts[i]);
        } else {
            System.out.println("There are no texts in the bulletin board!");
        }
        if (audios.length > 0) {
            System.out.println("AUDIOS:");
            for (int i = 0; i < audios.length; ++i) System.out.println(audios[i]);
        } else {
            System.out.println("There are no audios in the bulletin board!");
        }

        int [] v = {5, 2, 8, 3, 7, 4, 2, 7, 9, 4};
        int rc = Exercises2and3.firstRepeatedElement(v);
        if (rc == -1)
            System.out.println("No repeated elements found!");
        else
            System.out.println("First repeated element is " + rc);

        v[6] = 6;
        v[7] = 0;
        v[8] = 9;
        v[9] = 1;
        rc = Exercises2and3.firstRepeatedElement(v);
        if (rc == -1)
            System.out.println("No repeated elements found!");
        else
            System.out.println("First repeated element is " + rc);

        double [][] cases = {   {10.0, 1.0e-3},
                                {10.0, 1.0e-4},
                                {10.0, 1.0e-5},
                                {10.0, 1.0e-6},
                                {10.0, 1.0e-12},
                                {100.0, 1.0e-3},
                                {100.0, 1.0e-4},
                                {100.0, 1.0e-5},
                                {100.0, 1.0e-6},
                                {100.0, 1.0e-12},
                                {1000.0, 1.0e-3},
                                {1000.0, 1.0e-4},
                                {1000.0, 1.0e-5},
                                {1000.0, 1.0e-6},
                                {1000.0, 1.0e-12}
                            };
        for (int i = 0; i < cases.length; ++i) {
            double x = cases[i][0];
            double e = cases[i][1];
            int n = Exercises2and3.numTerms(x, e);

            System.out.printf("%d terms are required to adjust the function for x = %.2f and epsilon = %e\n", n, x, e);
        }
    }
}
