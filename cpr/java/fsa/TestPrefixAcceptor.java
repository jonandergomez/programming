package es.upv.etsinf.fsa;

import java.util.*; 

public class TestPrefixAcceptor
{
    public static void main(String [] args)
    {
        PrefixAcceptor pa1 = new PrefixAcceptor();
        pa1.add("aa");
        pa1.add("aaa");
        pa1.add("aaaa");
        pa1.compile();

        System.out.println();
        pa1.processText("aaaaaaaaaa");
        pa1.printResults();

        PrefixAcceptor pa2 = new PrefixAcceptor();
        pa2.add("aab");
        pa2.add("aabaab");
        pa2.compile();

        System.out.println();
        pa2.processText("aabaabaabaab");
        pa2.printResults();

        PrefixAcceptor pa3 = new PrefixAcceptor();
        pa3.add("ver");
        pa3.add("server");
        pa3.compile();

        System.out.println();
        pa3.processText("verserver");
        pa3.printResults();

        System.out.println();
    }
}
