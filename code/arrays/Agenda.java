import java.util.*;
import java.io.*;


public class Agenda
    implements Serializable
{
    private Contact []  data;
    private int         size;


    public Agenda()
    {
        this(100);
    }
    public Agenda(int initialSize)
    {
        data = new Contact [initialSize];
        size = 0;
    }

    public Contact getItemAt(int i)
    {
        return data[i];
    }
    public int size() { return size; }

    private void growArray()
    {
        // a new array larger than the existing one is created
        Contact [] newData = new Contact[data.length * 2];

        // all the elements in the existing array are copied
        // at the beginning of the new array
        for (int i = 0; i < data.length; i++) newData[i] = data[i];

        // the new array is now reference by the attribute 'data',
        // then the old array is released by the garbage collector
        data = newData;
    }
    public void add(Contact c)
    {
        if (size == data.length) growArray();

        data[size++] = c;
    }
    public void addInOrder(Contact c)
    {
        if (size == data.length) growArray();

        // Two versions of the shift to right
/*
        data[size++] = c;
        int i = size - 1;
        while (i > 0 && data[i].compareTo(data[i - 1]) < 0) {
            swap(i, i - 1);
            --i;
        }
*/
        int i = size - 1;
        while (i >= 0 && c.compareTo(data[i]) < 0) {
            data[i + 1] = data[i];
            --i;
        }
        data[i + 1] = c;
        ++size;
    }
    public void addInOrderUsingBirthDate(Contact c)
    {
        if (size == data.length) growArray();

        data[size++] = c;
        int i = size - 1;
        while (i > 0 && data[i].getDateOfBirth().compareTo(data[i - 1].getDateOfBirth()) < 0) {
            swap(i, i - 1);
            --i;
        }
    }
    public Agenda sort()
    {
        Agenda newAgenda = new Agenda();

        for (int i = 0; i < this.size(); i++) newAgenda.addInOrderUsingBirthDate(this.getItemAt(i));

        return newAgenda;
    }
    private void swap(int i, int j)
    {
        Contact temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public boolean remove(Contact c)
    {
        int pos = find(c);

        if (pos >= 0) {
            // Shift to the left
            for (int i = pos + 1; i < size; i++) {
                data[i - 1] = data[i];
            }
            data[size - 1] = null;
            --size;

            return true;
        }
        return false;
    }

    public int find(Contact c)
    {
        // Assuming the array is sorted in ascending order,
        // the Binary-Search is used
        int left = 0, right = size - 1;

        while (left <= right) {

            int k = (left + right) / 2;

            if (data[k].equals(c)) {
                return k;
            } else if (data[k].compareTo(c) < 0) {
                left = k + 1;
            } else {
                right = k - 1;
            }
        }
        return -1;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < size; i++) sb.append(data[i].toString() + "\n");

        return sb.toString();
    }


    public String listYoungerThan(int age)
    {
        StringBuffer sb = new StringBuffer();

        Calendar cal = Calendar.getInstance();

        Date reference = new Date(cal.get(Calendar.DAY_OF_MONTH),
                                  cal.get(Calendar.MONTH) + 1,
                                  cal.get(Calendar.YEAR));

        for (int i = 0; i < size; i++)
            if (data[i].getAge(reference) < age)
                sb.append(data[i].toString() + "\n");

        return sb.toString();
    }

    public String listOlderThan(int age)
    {
        StringBuffer sb = new StringBuffer();

        Calendar cal = Calendar.getInstance();

        Date reference = new Date(cal.get(Calendar.DAY_OF_MONTH),
                                  cal.get(Calendar.MONTH) + 1,
                                  cal.get(Calendar.YEAR));

        for (int i = 0; i < size; i++)
            if (data[i].getAge(reference) > age)
                sb.append(data[i].toString() + "\n");

        return sb.toString();
    }

    public String listPeopleWhoBornInMonth(int month)
    {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < size; i++)
            if (data[i].getMonthOfBirth() == month)
                sb.append(data[i].toString() + "\n");

        return sb.toString();
    }

    public Contact [] retrievePeopleWhoBornInMonth(int month)
    {
        int n = 0;

        for (int i = 0; i < size; i++)
            if (data[i].getMonthOfBirth() == month) n++;

        Contact [] bornInMonth = new Contact[n];

        n = 0;

        for (int i = 0; i < size; i++)
            if (data[i].getMonthOfBirth() == month)
                bornInMonth[n++] = data[i];

        return bornInMonth;
    }


    private void sort()
    {
        for (int i = 1; i < size; i++) {
            int j = i;
            Contact temp = data[i];
            while (j > 0 && data[j - 1].compareTo(temp) > 0) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = temp;
        }
    }
    public String showDuplicates()
    {
        sort();

        StringBuffer sb = new StringBuffer();

        for (int i = 1; i < size; i++)
            if (data[i].equals(data[i - 1]))
                sb.append(data[i].toString() + "\n");

        return sb.toString();
    }
}
