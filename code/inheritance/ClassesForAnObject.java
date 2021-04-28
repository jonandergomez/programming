/**
 * Class for illustrating to students the use of class <b>Class</b>
 * and some of its methods, with the purpose of obtaining the
 * genealogical tree of the class to which the object given as
 * parameter belongs.
 *
 * @author Jon Ander Gómez (jon@dsic.upv.es)
 * @version 1.0 (March 2012)
 */
public class ClassesForAnObject
{
    /** <b>String</b> object used for maintaining the indentation for preparing the tree. */
    private String indentation;
    /** Reference to the class <b>Object</b>, used for detecting the top of the hierarchy of Java classes. */
    private Class  root;

    /**
     * Constructor without parameters for initializing instance variables.
     */
    public ClassesForAnObject()
    {
        root = new Object().getClass();

        indentation="";
    }

    /**
     * Recursive method for building a tree representing the path in the hierarchy of Java classes.
     *
     * @param classz An object of the class <b>Class</b> as starting point for building the tree.
     *
     * @return A <b>String</b> representing a path in the hierarchy of classes from <b>Object</b>
     *         up to the class represented by the argument.
     */
    private String classesOf(Class classz)
    {
        if (classz != root) {

            String str = classesOf(classz.getSuperclass());

            str += "\n"
                 + indentation + "|\n"
                 + indentation
                 + "+-- " + classz.getName();

            indentation += "    ";

            return str;

        } else {

            indentation = "  ";
            return "+ " + classz.getName();
        }
    }

    /**
     * Static method for building the tree representing the path in the hierarchy of Java classes
     * starting in the root represented by the class <b>Object</b> up to the class of the object
     * given as parameter.
     * Shows on standard output the tree.
     *
     * @param obj Object of any class for building the tree.
     */
    public static void listOfClasses(Object obj)
    {
        ClassesForAnObject cfao = new ClassesForAnObject();

        String tree = cfao.classesOf(obj.getClass());

        System.out.println(tree + "\n");
    }
}
