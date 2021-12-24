import java.util.*;
import java.io.*;


public class TestAgenda
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);


    public static void main(String [] args)
        throws Exception
    {
        Agenda agenda = new Agenda();
        int opt, age, month;

        do {
            opt = menu();
            switch (opt) {
                case 1 :
                    addNewContact(agenda);
                    break;
                case 2 :
                    removeExistingContact(agenda);
                    break;
                case 3 :
                    System.out.println("\n");
                    System.out.println(agenda.toString());
                    System.out.println("\n\n Press enter to continue ... ");
                    input.nextLine();
                    break;
                case 4 :
                    System.out.print("\n reference age: ");
                    age = input.nextInt(); input.nextLine();
                    System.out.println("\n");
                    System.out.println(agenda.listYoungerThan(age));
                    System.out.println("\n\n Press enter to continue ... ");
                    input.nextLine();
                    break;
                case 5 :
                    System.out.print("\n reference age: ");
                    age = input.nextInt(); input.nextLine();
                    System.out.println("\n");
                    System.out.println(agenda.listOlderThan(age));
                    System.out.println("\n\n Press enter to continue ... ");
                    input.nextLine();
                    break;
                case 6 :
                    System.out.print("\n please enter the month in numbers: ");
                    month = input.nextInt(); input.nextLine();
                    System.out.println("\n");
                    System.out.println(agenda.listPeopleWhoBornInMonth(month));
                    System.out.println("\n\n Press enter to continue ... ");
                    input.nextLine();
                    break;
                case 7 :
                    System.out.println("\n");
                    System.out.println(agenda.showDuplicates());
                    System.out.println("\n\n Press enter to continue ... ");
                    input.nextLine();
                    break;
                case 8 :
                    saveToFile(agenda);
                    //saveToBinaryFile(agenda);
                    break;
                case 9 :
                    loadFromFile(agenda);
                    // agenda = loadFromBinaryFile();
                    break;
                case 0 : break;
                default :
                    System.out.println("Unrecognized option!!!\n");
            }
        } while (opt != 0);
    }

    public static int menu()
    {
        int opt;

        do {
            System.out.println("\n\n ---- MENU AGENDA ----\n\n");
            System.out.println("   1. Add new contact ");
            System.out.println("   2. Remove existing contact ");
            System.out.println("   3. List all the agenda ");
            System.out.println("   4. List all people younger than ... ");
            System.out.println("   5. List all people older than ... ");
            System.out.println("   6. List all people who was born on a month ");
            System.out.println("   7. Show duplicates ");
            System.out.println("   8. Save to file on disk ");
            System.out.println("   9. Load from file on disk ");
            System.out.println("   0. QUIT ");

            System.out.print("\n\n\t\t Select an option: ");
            opt = input.nextInt();
            input.nextLine();

        } while (opt < 0 || opt > 9);

        return opt;
    }

    public static void addNewContact(Agenda agenda)
    {
        System.out.print("Last name ............: "); String lastname = input.nextLine().trim();
        System.out.print("Name .................: "); String name = input.nextLine().trim();
        System.out.print("e-mail ...............: "); String email = input.nextLine().trim();
        System.out.print("Phone number .........: "); String phoneNumber = input.nextLine().trim();
        System.out.print("Birthdate DD MM YYYY .: "); int day = input.nextInt();
                                                      int month = input.nextInt();
                                                      int year = input.nextInt();

        agenda.addInOrder(new Contact(lastname, name, email, phoneNumber, new Date(day, month, year)));
    }

    public static void removeExistingContact(Agenda agenda)
    {
        System.out.print("Last name ............: "); String lastname = input.nextLine().trim();
        System.out.print("Name .................: "); String name = input.nextLine().trim();
        System.out.print("Birthdate DD MM YYYY .: "); int day = input.nextInt();
                                                      int month = input.nextInt();
                                                      int year = input.nextInt();

        agenda.remove(new Contact(lastname, name, "", "", new Date(day, month, year)));
    }

    public static void loadFromFile(Agenda agenda)
        throws Exception
    {
        Scanner sf = new Scanner(new File("agenda.txt"));

        while (sf.hasNextLine()) {

            String lastname = sf.nextLine().trim();
            String name = sf.nextLine().trim();
            String email = sf.nextLine().trim();
            String phoneNumber = sf.nextLine().trim();
            int year = sf.nextInt();
            int month = sf.nextInt();
            int day = sf.nextInt();
            sf.nextLine();

            System.err.println(lastname + ", " + name + " " + year + " " + month + " " + day);

            agenda.addInOrder(new Contact(lastname, name, email, phoneNumber,
                                            new Date(day, month, year)));
        }
        sf.close();
    }
    public static Agenda loadFromBinaryFile()
    {
        Agenda a = null;
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(new File("agenda.dat"));
            ois = new ObjectInputStream(fis);

            a = (Agenda) ois.readObject();
        }
        catch (IOException ioe) { System.err.println("ERROR trying to open or read from file! " + ioe.getMessage()); }
        catch (ClassNotFoundException cnfe) { System.err.println("ERROR: " + cnfe.getMessage()); }
        finally {
            try {
                if (ois != null) ois.close();
            }
            catch (IOException ioe) {
                System.err.println("ERROR trying to close file! " + ioe.getMessage());
            }
        }

        return a;
    }
    public static void saveToFile(Agenda agenda)
        throws Exception
    {
        PrintWriter pw = new PrintWriter(new File("agenda2.txt"));

        for (int i = 0; i < agenda.size(); i++) {
            Contact c = agenda.getItemAt(i);

            pw.println(c.getLastName());
            pw.println(c.getName());
            pw.println(c.getEMail());
            pw.println(c.getPhoneNumber());
            pw.println(c.getBirthDate().replaceAll("-", " "));
        }

        pw.close();
    }
    public static void saveToBinaryFile(Agenda agenda)
    {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("agenda.dat"));
            oos = new ObjectOutputStream(fos);

            oos.writeObject(agenda);
        }
        catch (IOException ioe) {
            System.err.println("ERROR trying to save the agenda into a file! "
                                + ioe.getMessage());
        }
        finally {
            try {
                if (oos != null) oos.close();
            }
            catch (IOException ioe) {
                System.err.println( "ERROR trying to close file! " + ioe.getMessage());
            }
        }
    }
}
