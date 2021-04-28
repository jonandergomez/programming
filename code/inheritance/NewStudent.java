public class NewStudent extends Student
{
    private float    averageMark;

    public NewStudent(String name, int age, float averageMark)
    {
        super(name, age);
        this.averageMark = averageMark;
    }
    @Override
    public String toString()
    {
        // Typical and common reuse of the toString() method of the parent class
        //return super.toString() + " averageMark: " + averageMark;

        // Martingale to use a equivalent static toString() method from
        // the grant-parent class
        return Person.toString(this) + " averageMark: " + averageMark;

        // Useless martingale to use the toString() method from the
        // grant-parent class. This generates a stack overflow because
        // invoking the toString() this way is a inconditional recursive call
        // return ((Person)this).toString() + " averageMark: " + averageMark;
    }
}
