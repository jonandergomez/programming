
import java.io.*;

class SeeDirectory
{
    public static void main(String [] args)
    {
        File d = new File(".");

        if (d.exists())
            System.out.println("File exists!");
        else
            System.err.println("File doesn’t exist!");

        System.err.println();

        System.out.println("  getName(): " + d.getName());
        System.out.println("getParent(): " + d.getParent());
        System.out.println("   length(): " + d.length());

        if (d.isDirectory()) {
            System.out.println("--------------------------------");
            for (File f : d.listFiles()) {
                System.out.println(f);
            }
            System.out.println("--------------------------------");
        }

        wipeFiles("../../..");
    }

    public static void wipeFiles(String dirname)
    {
        File file = new File(dirname);

        if (file.exists()) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    wipeFiles(f.getParent() + "/" + f.getName());
                }
            } else {
                // This is very dangerous because this will wipe all files it encounters
                // Don't try it please, this is just an example.
                //PrintWriter pw = new PrintWriter(file);
                //pw.close();
                System.out.println(file);
            }
        }
    }
}
