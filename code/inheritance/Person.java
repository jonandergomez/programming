public class Person
{
    private String name;
    private int    age;

    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString()
    {
        return "Name: " + name + " Age: " + age;
    }

    // see how this method is reused in class NewStudent
    public static String toString(Person p)
    {
        return "Name: " + p.name + " Age: " + p.age;
    }
}
