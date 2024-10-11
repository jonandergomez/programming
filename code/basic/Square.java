public class Square extends Rectangle {

    public Square() {
        super(1, 1, 50, 50);
    }
    public Square(double side, double x, double y) {
        super(side, side, x, y);
    }

    public double getSide() { return this.getBase(); }

    public void setSide(double s) { this.setBase(s); this.setHeight(s); }

    @Override
    public String toString() {
        return "Square centred at "
             + this.getCentre().toString()
             + " with sides of = " + this.getSide() + " m";
    }
}
