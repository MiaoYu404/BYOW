package tileengine;

public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() { return this.x; }
    public int y() { return this.y; }

    public void setX(int val) { this.x = val; }
    public void setY(int val) { this.y = val; }

    public Coordinate copy() { return new Coordinate(this.x, this.y); }

    public Coordinate plus(int x, int y) { return new Coordinate(x + this.x, y + this.y); }
    public Coordinate plus(Coordinate other) { return new Coordinate(x + other.x, y + other.y); }
    @Override
    public boolean equals(Object other) {
        if (other instanceof Coordinate o) {
            return x == o.x() && y == o.y();
        }
        return false;
    }

    @Override
    public String toString() { return String.format("(%d,%d)", x, y); }
}
