public class B extends A {
    private int z;

    public B(int x, int y, int z) {
        super(x, y); // invokes the constructor of the parent class
        this.z = z;
    }

    public int sum() {
        return this.getX() + this.getY() + z;
    }

    public int product() {
        return super.product() * z;
    }
}
