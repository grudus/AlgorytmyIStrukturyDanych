import static java.lang.String.format;

public class Jednomian implements Comparable<Jednomian> {
    private final int skalar;
    private final int x;
    private final int y;
    private final int z;

    public Jednomian(int skalar, int x, int y, int z) {
        this.skalar = skalar;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int compareTo(Jednomian o) {
        int xDiff = x - o.x;
        if (xDiff != 0)
            return xDiff;

        int yDiff = y - o.y;
        if (yDiff != 0)
            return yDiff;

        return z - o.z;
    }
    @Override
    public String toString() {
        return format("%s%d*x^(%d)*y^(%d)*z^(%d)", skalar > 0 ? " + " : " - ", Math.abs(skalar), x, y, z);
    }
}
