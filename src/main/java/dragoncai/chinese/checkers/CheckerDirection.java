package dragoncai.chinese.checkers;

/**
 * Created by SAC D'AUBER on 23/04/2016.
 */
public enum CheckerDirection {
    NORTH_EAST(0, 1, 1),
    NORTH_WEST(1, 1, 0),
    WEST(1, 0, -1),
    EAST(-1, 0, 1),
    SOUTH_EAST(-1, -1, 0),
    SOUTH_WEST(0, -1, -1);

    private IPosition position;

    CheckerDirection(int xLeft, int y, int xRight) {
        this.position = Position.newInstance(xLeft, y, xRight);
    }

    public IPosition getPosition() {
        return position;
    }

    public CheckerDirection opposite(){
        switch (this) {
            case NORTH_EAST:
                return SOUTH_WEST;
            case NORTH_WEST:
                return SOUTH_EAST;
            case SOUTH_EAST:
                return NORTH_WEST;
            case SOUTH_WEST:
                return NORTH_EAST;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                return null;
        }
    }
}
