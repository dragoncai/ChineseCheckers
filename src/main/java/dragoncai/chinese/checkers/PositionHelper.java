package dragoncai.chinese.checkers;

/**
 * Created by SAC D'AUBER on 16/04/2016.
 */
public class PositionHelper {
    public static IPosition sumPositions(IPosition... positions) {
        int sumXLeft=0;
        int sumXRight=0;
        int sumY=0;

        for (IPosition position : positions) {
            sumXLeft += position.getXLeft();
            sumXRight += position.getXRight();
            sumY += position.getY();
        }

        return Position.newInstance(sumXLeft, sumY, sumXRight);
    }

    public static IPosition simpleJumpPosition(IPosition currentPosition, CheckerDirection direction) {
        return sumPositions(currentPosition, direction.getPosition());
    }

    public static IPosition jumpOverPiecePosition(IPosition currentPosition, CheckerDirection direction) {
        return sumPositions(currentPosition, direction.getPosition(), direction.getPosition());
    }


}
