/*
    mkdir docs
    javadoc -d docs -private Point.java
    firefox docs/index.html
*/

public class Point
{
    /** attribute for abscissas, x-axis */
    private double x;
    /** attribute for ordinates, y-axis */
    private double y;

    /**
     * Initializes an object of class <code>Point</code>
     * with initial values for attributes
     * <code>x</code> and <code>y</code>.
     *
     * @param x value for x-axis
     * @param y value for y-axis
     */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    /**
     * Initializes an object of class <code>Point</code>
     * for representing a point in the origin of coordinates.
     */
    public Point()
    {
        this(0.0, 0.0);
    }

    /**
     * Returns the value of private attribute <code>x</code>.
     *
     * @return The value of <code>x</code>.
     */
    public double getX() { return x; }
    /**
     * Returns the value of private attribute <code>y</code>.
     *
     * @return The value of <code>y</code>.
     */
    public double getY() { return y; }

    /**
     * Sets the value of private attribute <code>x</code>.
     *
     */
    public void setX(double x) { this.x = x; }
    /**
     * Sets the value of private attribute <code>y</code>.
     *
     */
    public void setY(double y) { this.y = y; }

    /**
     * Returns the distance between the current object
     * and another one provided as a parameter to this method.
     *
     * @param other A reference variable pointing to an object of the class
     *              <code>Point</code>.
     *
     * @return The distance between <code>this</code> and <code>other</code>.
     */
    public double distance(Point other)
    {
        double dx = this.x - other.x;
        double dy = this.y - other.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns the distance of the point represented by the current object
     * to the origin of coordinates.
     *
     * @return The distance between <code>this</code> and <code>(0,0)</code>.
     */
    public double distanceFromOrigin()
    {
        // A temporary object of class Point is created for representing
        // the origin of coordinates: (0,0)
        return distance(new Point());
    }

    /**
     * Moves the position of the current point by shifting on x-axis by
     * <code>deltaX</code> and y-axis by <code>deltaY</code>.
     *
     * @param deltaX  a real value for modifying the value of <code>x</code> attribute.
     * @param deltaY  a real value for modifying the value of <code>y</code> attribute.
     */
    public void move(double deltaX, double deltaY)
    {
        this.x += deltaX;
        this.y += deltaY;
    }

    /**
     * Moves the point randomly.
     */
    public void moveRandom()
    {
        move(random(), random());
    }

    /**
     * Returns a real value in the range <code>[-d,d]</code> where
     * <code>d</code> is the distance from the origin plus one.
     *
     * @return a random value limited in magnitude by the distance of this point to the origin of coordinates.
     */
    private double random()
    {
        return (Math.random() - 0.5) * (2 * distanceFromOrigin() + 1);
    }

    /**
     * Returns an object of class <code>String</code> representing the
     * current point.
     */
    @Override
    public String toString()
    {
        return String.format("( %.2f, %.2f )", x, y);
    }

    /**
     * Returns whether the object passed as argument is equal to <code>this</code>.
     *
     * @param other A reference variable to objects of the class <code>Point</code>.
     * @return <code>true</code> if both objects are equal, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Point) {
            Point p = (Point)other;

            return this.x == p.x && this.y == p.y;

        } else {
            return false;
        }
    }
}
