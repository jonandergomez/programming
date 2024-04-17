public class TestStudent {
    public static void main(String[] args) {
        Student s = new Student("John Smith", 19);

        System.out.printf("Person: %s\n", s.toString());
        System.out.printf("%s is enrolled in %d ECTS.\n",
                s.getName(), s.getECTS());
    }
}