import etsinf.prg.agenda3.*;

import java.util.*;
import java.io.*;

/**
  * Class for testing the different functionalities provided by the classes
  * included in the package <code>agenda3</code>.
  */
public class TestingAgenda3
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        Agenda agenda = new Agenda();
        int opt;

        do {
            opt = menu();
            switch (opt) {
                case 1 : addNewContact(agenda); break;
                case 2 : removeExistingContact(agenda); break;
                case 3 : listContactsInAgenda(agenda); break;
                case 4 : listYoungerThan(agenda); break;
                case 5 : listOlderThan(agenda); break;
                case 6 : listPeopleWhoBornInMonth(agenda); break;
                case 7 : listDuplicates(agenda); break;
                case 8 : listEventsInAgenda(agenda); break;
                case 9 : listUpcomingEvents(agenda); break;

                case 10 : saveToFile(agenda); break;
                case 11 : loadFromFile(agenda); break;

                case 0 : break;

                default : System.out.println( "Unrecognized option!!!\n" );
            }
        } while(opt != 0);
    }

    private static int menu()
    {
        int opt;

        do {
            System.out.println("\n\n ---- MENU AGENDA ----\n\n");
            System.out.println("   1. Add new contact ");
            System.out.println("   2. Remove existing contact ");
            System.out.println("   3. List all people in the agenda ");
            System.out.println("   4. List people younger than ... ");
            System.out.println("   5. List people older than ... ");
            System.out.println("   6. List people who was born on a month ");
            System.out.println("   7. Show duplicates ");
            System.out.println("   8. List all events in the agenda ");
            System.out.println("   9. List upcoming events in the agenda ");
            System.out.println("  10. Save to file on disk ");
            System.out.println("  11. Load from file on disk ");
            System.out.println("   0. QUIT ");

            System.out.print( "\n\n\t\t Select an option: " );
            opt = input.nextInt();
            input.nextLine();

        } while (opt < 0 || opt > 20);

        return opt;
    }

    private static void addNewContact(Agenda agenda)
    {
        System.out.print("Last name ............: "); String lastname = input.nextLine().trim();
        System.out.print("Name .................: "); String name = input.nextLine().trim();
        System.out.print("e-mail ...............: "); String email = input.nextLine().trim();
        System.out.print("Phone number .........: "); String phoneNumber = input.nextLine().trim();
        System.out.print("Birthdate DD MM YYYY .: "); int day = input.nextInt();
                                                      int month = input.nextInt();
                                                      int year = input.nextInt();
                                                      input.nextLine();

        agenda.addInOrder(new Contact(lastname, name, email, phoneNumber,
                                      new etsinf.prg.agenda3.Date(day, month, year)));
    }

    private static void removeExistingContact(Agenda agenda)
    {
        System.out.print("Last name ............: "); String lastname = input.nextLine().trim();
        System.out.print("Name .................: "); String name = input.nextLine().trim();
        System.out.print("Birthdate DD MM YYYY .: "); int day = input.nextInt();
                                                      int month = input.nextInt();
                                                      int year = input.nextInt();
                                                      input.nextLine();

        agenda.remove(new Contact(lastname, name, "", "", new etsinf.prg.agenda3.Date(day, month, year)));
    }

    private static void listContactsInAgenda(Agenda agenda)
    {
        System.out.println("\n");
        System.out.println(agenda.toString());
        System.out.println("\n\n Press enter to continue ... ");
        input.nextLine();
    }
    private static void listOlderThan(Agenda agenda)
    {
        System.out.print("\n reference age: ");
        int age = input.nextInt(); input.nextLine();
        System.out.println("\n");
        System.out.println(Agenda.toString(agenda.getOlderThan(age)));
        System.out.println("\n\n Press enter to continue ... ");
        input.nextLine();
    }
    private static void listYoungerThan(Agenda agenda)
    {
        System.out.print("\n reference age: ");
        int age = input.nextInt(); input.nextLine();
        System.out.println("\n");
        System.out.println(Agenda.toString(agenda.getYoungerThan(age)));
        System.out.println("\n\n Press enter to continue ... ");
        input.nextLine();
    }
    private static void listPeopleWhoBornInMonth(Agenda agenda)
    {
        System.out.print("\n please enter the month in numbers: ");
        int month = input.nextInt(); input.nextLine();
        System.out.println("\n");
        System.out.println(Agenda.toString(agenda.getPeopleWhoBornInMonth(month)));
        System.out.println("\n\n Press enter to continue ... ");
        input.nextLine();
    }
    private static void listDuplicates(Agenda agenda)
    {
        Contact [] duplicateContacts = agenda.getDuplicateContacts();
        Event [] duplicateEvents = agenda.getDuplicateEvents();

        if (duplicateContacts.length == 0) {
            System.out.println("There are no duplicate contacts.");
        } else {
            System.out.println("\n");
            System.out.println("List of the " + duplicateContacts.length + " found duplicate contacts:");
            System.out.println("\n");
            System.out.println(Agenda.toString(duplicateContacts));
            System.out.println("\n");
        }

        if (duplicateEvents.length == 0) {
            System.out.println("There are no duplicate events.");
        } else {
            System.out.println("\n");
            System.out.println("List of the " + duplicateEvents.length + " found duplicate events:");
            System.out.println("\n");
            System.out.println(Agenda.toString(duplicateEvents));
            System.out.println("\n");
        }
        System.out.println( "\n\n Press enter to continue ... " );
        input.nextLine();
    }

    private static void listEventsInAgenda(Agenda agenda)
    {
        System.out.println("\n");
        System.out.println(agenda.toStringEvents());
        System.out.println( "\n\n Press enter to continue ... " );
        input.nextLine();
    }
    private static void listUpcomingEvents(Agenda agenda)
    {
        System.out.println("\n");
        System.out.println(Agenda.toString(agenda.getUpcomingEvents()));
        System.out.println( "\n\n Press enter to continue ... " );
        input.nextLine();
    }

    private static void saveToFile(Agenda agenda)
    {
        try {
        PrintWriter pw = new PrintWriter(new File("agenda.txt"));

        pw.println(agenda.getPeopleSize());

        for (int i = 0; i < agenda.getPeopleSize(); ++i) {
            Contact c = agenda.getContactAt(i);
            pw.println(c.getLastName());
            pw.println(c.getName());
            pw.println(c.getEMail());
            pw.println(c.getPhoneNumber());
            pw.println(c.getBirthDate().replaceAll("-", " "));
        }

        pw.println("####");

        pw.println(agenda.getEventsSize());

        for (int i = 0; i < agenda.getEventsSize(); ++i) {
            Event e = agenda.getEventAt(i);

            pw.println(e.getTitle());
            pw.println(e.getLocation());
            pw.println(e.getStart());
            pw.println(e.getEnd());
            pw.println(e.getAttendants());
        }

        pw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }
    private static void loadFromFile(Agenda agenda)
    {
        try {
        Scanner sf = new Scanner(new File("agenda.txt"));

        int n = sf.nextInt(); sf.nextLine();
        while (--n >= 0) {
            String lastname = sf.nextLine().trim();
            String name = sf.nextLine().trim();
            String email = sf.nextLine().trim();
            String phoneNumber = sf.nextLine().trim();
            int year = sf.nextInt();
            int month = sf.nextInt();
            int day = sf.nextInt();
            sf.nextLine();

            Contact c = new Contact(lastname, name, email, phoneNumber, new etsinf.prg.agenda3.Date(day, month, year));
            System.out.println("Loaded " + c);
            agenda.addInOrder(c);
        }
        sf.nextLine(); // to skip ####
        n = sf.nextInt(); sf.nextLine();
        while (--n >= 0) {
            String title = sf.nextLine().trim();
            String location = sf.nextLine().trim();
            Timestamp start = new Timestamp(sf.nextLine().trim());
            Timestamp   end = new Timestamp(sf.nextLine().trim());
            int attendants = sf.nextInt(); sf.nextLine();

            System.out.println(start + " ---> " + end);

            Event e = new Event(start, end, title, location);
            while (--attendants > 0) e.addAttendant();
            System.out.println("Loaded " + e);
            agenda.addInOrder(e);
        }
        sf.close();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
            System.exit(-1);
        }
    }
}
