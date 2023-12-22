import java.util.*;
import java.io.*;

public class Contact
    implements Serializable,Comparable<Contact> // You have to believe this is correct!
{
    private String lastname;
    private String name;
    private String email;
    private String phoneNumber;
    private Date   birthDate;

    public Contact(String lastname, String name, String email,
                   String phoneNumber, Date birthDate)
    {
        this.lastname = lastname;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate.clone();
    }

    public String getCanonicalName()
    {
        return lastname + ", " + name;
    }
    public String getLastName() { return lastname; }
    public String getName() { return name; }
    public String getEMail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getBirthDate() { return birthDate.toString(); }
    public int    getAge(Date reference) { return birthDate.getAge(reference); }

    public Date getDateOfBirth() { return birthDate; }

    public int getMonthOfBirth() { return birthDate.getMonth(); }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Contact) {
            Contact other = (Contact)o;

            return this.lastname.equals(other.lastname)
                && this.name.equals(other.name)
                // && this.email.equals(other.email)
                // && this.phoneNumber.equals(other.phoneNumber)
                && this.birthDate.equals(other.birthDate);
        }
        return false;
    }

    public int compareTo(Contact other)
    {
        return this.getCanonicalName().compareTo(other.getCanonicalName());
    }

    @Override
    public String toString()
    {
        return String.format("%s  %-30.30s  %-20.20s  %s",
                             birthDate.toString(),
                             getCanonicalName(),
                             email,
                             phoneNumber);
    }
}
