import org.w3c.dom.css.Rect;

public class Rectangle {

    private Point  centre;
    private double height, base;

    public Rectangle() {
        height = base = 1;
        centre = new Point(50, 50);
    }

    public Rectangle(double h, double b, double x, double y) {
        height = h;
        base = b;
        centre = new Point(x, y);
    }

    public Rectangle(Point llc, Point urc) {
        height = Math.abs(urc.getY() - llc.getY());
        base   = Math.abs(urc.getX() - llc.getX());
        centre = new Point((llc.getX() + urc.getX()) / 2.0, (llc.getY() + urc.getY()) / 2.0);
    }

    public Point getCentre() { return centre; }
    public double getHeight() { return height; }
    public double getBase() { return base; }

    public void setCentre(double x, double y) {
        centre = new Point(x, y);
    }
    public void setHeight(double h) { height = h; }
    public void setBase(double b) { base = b; }

    public Point getLowerLeftCorner() {
        return new Point(centre.getX() - base / 2, centre.getY() - height / 2);
    }
    public Point getUpperRightCorner() {
        return new Point(centre.getX() + base / 2, centre.getY() + height / 2);
    }

    @Override
    public String toString() {
        return "Rectangle centred at "
             + centre.toString()
             + " with base = " + base + " m"
             + " and height " + height + " m";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Rectangle) { // Check o is an object of the class Rectangle
            Rectangle other = (Rectangle)o; // Refer to it as an object of the class Rectangle

            return this.centre.equals(other.centre)
                && Math.abs(this.base - other.base) <= Point.EPSILON
                && Math.abs(this.height - other.height) <= Point.EPSILON;
        } else {
            return false;
        }
    }
}
