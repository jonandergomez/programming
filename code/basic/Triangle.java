import javax.swing.event.TableColumnModelListener;

public class Triangle {
    private Point A, B, C;

    public Triangle() {
        A = new Point(-1, 0);
        B = new Point(1, 0);
        C = new Point(0, 1);
    }

    public Triangle(Point A, Point B, Point C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public Point getA() { return A; }
    public Point getB() { return B; }
    public Point getC() { return C; }

    public void setA(Point other) { A = other; }
    public void setB(Point other) { B = other; }
    public void setC(Point other) { C = other; }

    @Override
    public String toString() {
        return "Triangle formed by points: " + A + " and " + B + " and " + C;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Triangle) {
            Triangle other = (Triangle)o;

            // This is wrong, will fail in most of cases
            return this.A.equals(other.A)
                && this.B.equals(other.B)
                && this.C.equals(other.C);
        } else {
            return false;
        }
    }

    public double area() {
        double area = A.getX() * B.getY() - A.getY() * B.getX()
                    - B.getX() * C.getY() + B.getY() * C.getX()
                    + C.getX() * A.getY() - C.getY() * A.getX();

        return Math.abs(area) / 2.0;
    }
}
