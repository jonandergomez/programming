public class TestStudent3 {
    public static void main(String[] args) {
        Student s = new Student("John Smith", 19);
        Person  p = new Person("John Smith", 19);
        Person  q = new Student("Patty Smith", 39);
        NewStudent ns = new NewStudent("Patty Smith", 39, 9.999F);

        System.out.println("Person----: " + p.toString());
        System.out.println("Student---: " + s.toString());
        System.out.println("----------: " + q.toString());
        System.out.println("----------: " + q);
        System.out.println("NewStudent: " + ns);

        System.out.println();
        ClassesForAnObject.listOfClasses(ns);
    }
}
