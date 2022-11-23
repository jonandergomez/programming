import java.util.*;

import javax.swing.text.LayeredHighlighter.LayerPainter;

import java.io.*;

public class Date implements Serializable
{
    private int year, month, day;

    public Date(int d, int m, int y)
    {
        year = y;
        month = m;
        day = d;
    }

    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }

    @Override
    public String toString()
    {
        return String.format("%04d-%02d-%02d", year, month, day);
    }
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Date) {
            Date other = (Date)o;

            return this.year == other.year
                && this.month == other.month
                && this.day == other.day;
        }
        return false;
    }
    public int compareTo(Date other)
    {
        return this.toInt() - other.toInt();
    }
    public int toInt()
    {
        return year * 10000 + month * 100 + day;
    }

    public Date clone()
    {
        return new Date(day, month, year);
    }

    public int getAge(Date reference)
    {
        int age = reference.year - this.year;

        if (this.month * 100 + this.day < reference.month * 100 + reference.day)
            age--;

        return age;
    }

    public static boolean leapYear(int y)
    {
        return y % 4 == 0 && y % 100 != 0 && y % 400 == 0;
    }
    public boolean leapYear()
    {
        return leapYear(this.year);
    }

    public Date nextDay()
    {
        int d = this.day + 1;
        int m = this.month;
        int y = this.year;

        int daysOfMonth = 31;
        switch(m) {
            case  2:
                daysOfMonth = 28;
                if (leapYear(y)) daysOfMonth++;
                break;

            case  4:
            case  6:
            case  9:
            case 11: daysOfMonth = 30; break;

            default : daysOfMonth = 31;
        }
        if (d > daysOfMonth) {
            d = 1;
            m++;
            if (m > 12) {
                m = 1;
                y++;
            }
        }
        return new Date(d, m, y);
    }

    public static void main(String [] args)
    {
        Date today = new Date(31, 12, 2022);

        Date tomorrow = today.nextDay();

        System.out.println(today);
        System.out.println(tomorrow);
    }

}







