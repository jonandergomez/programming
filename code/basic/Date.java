
public class Date
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
}
