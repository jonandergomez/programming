import java.io.*;

class TestFile
{
    public static void main(String [] args)
    {
        File f = new File("/home/jon/file.txt");
        //File f = new File("../test_dir/file4323.txt");
        //File f = new File("/root/.bashrc");

        if (f.exists())
            System.out.println("File exists!");
        else
            System.err.println("File doesn’t exist!");

        System.err.println();

        System.out.println("  getName(): " + f.getName());
        System.out.println("getParent(): " + f.getParent());
        System.out.println("   length(): " + f.length());
    }
}