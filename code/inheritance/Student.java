public class Student extends Person {
    private int ects;

    public Student(String name, int age) {
        super(name, age);
        this.ects = 60;
    }

    public int getECTS() {
        return ects;
    }

    @Override
    public String toString() {
        /*
         * option 1: complete override *
         * return this.getName() + " "
         * + this.getAge()
         * + " ECTS: " + ects;
         */
        /* option 2: partial override -- the preferred one */
        return super.toString() + " ECTS: " + ects;

        /*
         * option 3: partial override but
         * using an existing object
         * of the parent class *
         * return Person.toString(this)
         * + " ECTS: " + ects;
         */
    }
}
