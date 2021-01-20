
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
    }
}
