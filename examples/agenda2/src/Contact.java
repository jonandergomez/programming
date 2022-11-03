package etsinf.prg.agenda2;

/**
 * Class designed to represent contacts to be included in the list of contacts of an agenda.
 */
public class Contact
    implements Comparable<Contact> // You have to believe this is correct!
{
    /** Last name of a friend o other person in our list of contacts. */
    private String lastname;
    /** First name of a friend o other person in our list of contacts. */
    private String name;
    /** Electronic mail of a friend o other person in our list of contacts. */
    private String email;
    /** Phone number of a friend o other person in our list of contacts. */
    private String phoneNumber;
    /** Birth date of a friend o other person in our list of contacts. */
    private Date   birthDate;

    /**
      * Constructor that creates a new object of this class from the values
      * for each attribute provided as parameters.
      *
      * @param lastname An string with the last name of the new contact.
      * @param name An string with the first name of the new contact.
      * @param email An string with the email address of the new contact.
      * @param phoneNumber An string with the phone number of the new contact.
      * @param birthDate An object of the class <code>Date</code> with the
      *                  birth date of the new contact.
      */
    public Contact(String lastname, String name,
                   String email, String phoneNumber,
                   Date birthDate)
    {
        this.lastname = lastname;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate.clone();
        // the birth date is cloned to avoid non-expected modifications
    }

    /**
      * Returns the complete name of the person in the format
      * <em>lastname, name</em>, e.g. <em>Lenon, John</em>.
      *
      * @return An string with the canonical name of a person.
      */
    public String getCanonicalName()
    {
        return lastname + ", " + name;
    }
    /**
      * Returns the last name of a person.
      * @return An string with the last name.
      */
    public String getLastName() { return lastname; }
    /**
      * Returns the first name of a person.
      * @return An string with the first name.
      */
    public String getName() { return name; }
    /**
      * Returns the email address of a person.
      * @return An string with the email address.
      */
    public String getEMail() { return email; }
    /**
      * Returns the phone number of a person.
      * @return An string with the phone number.
      */
    public String getPhoneNumber() { return phoneNumber; }
    /**
      * Returns the birth date of a person.
      * @return An string representing the birth date.
      */
    public String getBirthDate() { return birthDate.toString(); }
    /**
      * Returns the age of a person.
      * @return An integer with the date of a person computed with respect today.
      */
    public int getAge(Date reference) { return birthDate.getAge(reference); }

    /**
      * Returns the month of the birth date of a person.
      * @return An integer with the value of the month the person was born.
      */
    public int getMonthOfBirth() { return birthDate.getMonth(); }

    /**
      * Returns whether the object passed as parameter is an object of the class
      * <code>Contact</code> and the attributes <code>lastname</code>,
      * <code>name</code>, and <code>birthDate</code> match.
      *
      * @return <code>true</code> if both objects are equal,
      *         <code>false</code> otherwise.
      */
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

    /** Compares two objects of this class according to the alphabetical order
      * of the canonical name.
      * <ul>
      * <li> &lt;0 if <code>this</code> should go before <code>other</code>. </li>
      * <li>     0 if both objects have the same canonical name. </li>
      * <li> &gt;0 if <code>this</code> should go after <code>other</code>. </li>
      * </ul>
      *
      * @param other A reference to another object of the class <code>Contact</code>.
      * @return A negative integer value when the current object should go before
      *         the other object given as parameter in a alphabetically sorted list,
      *         zero when both objects have the same canonical name, and a positive
      *         value when the current object should go after the other in a sorted list.
      */
    public int compareTo(Contact other)
    {
        return this.getCanonicalName().compareTo(other.getCanonicalName());
    }

    /** Returns representation of the contact including all the attributes.
      *
      * @return A reference to a new object of the class <code>String</code>
      *         created when this method is invoked using the current values of
      *         the attributes of this contact.
      */

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
