import java.util.*;

public class TestCircle
{
    public static void main(String [] args)
    {
        Circle c1 = new Circle();
        Circle c2 = new Circle(5.7, "turquoise", 157, 52);

        System.out.println(c1.toString());
        System.out.println(c2.toString());

        c1.setCentre(7,6);
        System.out.println("Radius  " + c1.getRadius());
        System.out.println("Colour  " + c1.getColour());
        System.out.println("CenterX " + c1.getCentreX());
        System.out.println("CenterY " + c1.getCentreY());
        System.out.println(c1.toString());

        /* The following lines will raise a compiler error
           because they try to access private attributes.
           
        System.out.println("Radius  " + c1.radius);
        System.out.println("Colour  " + c1.colour);
        System.out.println("CenterX " + c1.centreX);
        System.out.println("CenterY " + c1.centreY);
        */

        System.out.println();

        System.out.println(Math.E);
        System.out.println(Math.PI);
    }
}
