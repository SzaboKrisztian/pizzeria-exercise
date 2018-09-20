import java.lang.Math;

public class Coordinate {
    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double distanceTo(Coordinate other) {
        return distanceBetween(this, other);
    }

    public static double distanceBetween(Coordinate loc1, Coordinate loc2) {
        return Math.sqrt(
        Math.pow(Math.abs(loc1.getX() - loc2.getX()), 2) +
        Math.pow(Math.abs(loc1.getY() - loc2.getY()), 2));
    }

    public Coordinate clone() {
        return new Coordinate(this.x, this.y);
    }
}
