public class Student extends Person
{
    private int    ects;

    public Student(String name, int age)
    {
        super(name, age);
        this.ects = 60;
    }
    public int getECTS() { return ects; }

    @Override
    public String toString()
    {
        return super.toString() + " ECTS: " + ects;
    }
}
