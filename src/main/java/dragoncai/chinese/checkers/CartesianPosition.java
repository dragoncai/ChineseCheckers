package dragoncai.chinese.checkers;

/**
 * Created by SAC D'AUBER on 23/04/2016.
 */
public class CartesianPosition {
    private double x;
    private double y;

    public CartesianPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public CartesianPosition(IPosition position) {
        this((position.getXRight() - position.getXLeft()) / java.lang.Math.sqrt(3), position.getY());
    }

    public int getX() {
        return (int)(11*25+x*25);
    }

    public int getY() {
        return (int)(100+y*25);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartesianPosition that = (CartesianPosition) o;

        if (Double.compare(that.x, x) != 0) return false;
        return Double.compare(that.y, y) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf("" + x + '\t' + y);
    }
}
