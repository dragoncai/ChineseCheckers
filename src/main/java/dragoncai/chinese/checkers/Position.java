package dragoncai.chinese.checkers;

/**
 * Created by SAC D'AUBER on 16/04/2016.
 */
public class Position implements IPosition {
    private int xLeft;
    private int y;
    private int xRight;


    private Position(int xLeft, int y, int xRight) {
        this.xLeft = xLeft;
        this.y = y;
        this.xRight = xRight;
    }

    @Override
    public int getXLeft() {
        return xLeft;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getXRight() {
        return xRight;
    }

    public static IPosition newInstance(int xLeft, int y, int xRight) {
        return new Position(xLeft, y, xRight);
    }

    @Override
    public String toString() {
        return "Position{" +
                "xLeft=" + xLeft +
                ", y=" + y +
                ", xRight=" + xRight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (xLeft != position.xLeft) return false;
        if (y != position.y) return false;
        return xRight == position.xRight;

    }

    @Override
    public int hashCode() {
        int result = xLeft;
        result = 31 * result + y;
        result = 31 * result + xRight;
        return result;
    }
}
