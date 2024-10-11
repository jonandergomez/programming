/**
 * Class Circle2: defines a circle with a radius, a color and the position of this centre,
 * with the functionality described by the methods.
 * @author IIP-PRG book
 * @version 2018
 */
public class Circle2 {

    private double radius;
    private String colour;
    private Point  centre;

    /** Creates a black circle with radius=50 and centered at (100,100). */
    public Circle2()
    {
        radius = 50;
        colour = "black";
        centre = new Point(100, 100);
    }

    /** Creates a generic circle with radius r, colour c and centred at (px,py). */
    public Circle2(double r, String c, double px, double py)
    {
        radius = r;
        colour = c;
        centre = new Point(px, py);
    }

    /** Returns the value of the radius attribute. */
    public double getRadius() { return radius; }

    /** Returns the color of the Circle2. */
    public String getColour() { return colour; }

    /** Returns the x-coordinate of the center of the circle. */
    public double getCentreX() { return centre.getX(); }

    /** Returns the y-coordinate of the center of the circle. */
    public double getCentreY() { return centre.getY(); }

    /** Returns the point representing the centre of the circle. */
    public Point getCentre() { return centre; }

    /** Updates the radius of the circle. */
    public void setRadius(double newRadius) { radius = newRadius; }

    /** Updates the color of the circle. */
    public void setColour(String newColour) { colour = newColour; }

    /** Updates the coordinates of the center of the circle. */
    public void setCentre(double px, double py) { centre = new Point(px, py); }

    /** Shifts the circle to the right. */
    public void moveToRight() { centre.setX(centre.getX() + 10); }
    /** Increases the radius of the circle. */
    public void grow() { radius = radius * 1.3; }
    /** Decreases the radius of the circle. */
    public void shrink() { radius = radius / 1.3; }

    /** Returns the area of the circle. */
    public double area() { return Math.PI * radius * radius; }

    /** Returns the perimeter of the circle. */
    public double perimeter() { return 2 * Math.PI * radius; }

    /** Returns an string which is a representation of the circle. */
    public String toString()
    {
        String res = "Circle with radius "+ radius;
        res += ", colour " + colour
            + " and centred at " + centre.toString();
        return res;
    }
}
