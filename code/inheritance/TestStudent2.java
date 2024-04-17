public class TestStudent2 {
    public static void main(String[] args) {
        Student s = new Student("John Smith", 19);
        Person p = new Person("John Smith", 19);

        System.out.println("Person.: " + p.toString());
        System.out.println("Student: " + s.toString());
    }
}
