package dragoncai.chinese.checkers;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * Created by SAC D'AUBER on 16/04/2016.
 */
public class CheckerBoard {

    public static final ArrayList<CheckerDirection> CHECKER_DIRECTIONS = Lists.newArrayList(CheckerDirection.NORTH_WEST,
            CheckerDirection.NORTH_WEST,
            CheckerDirection.NORTH_WEST,
            CheckerDirection.EAST,
            CheckerDirection.EAST,
            CheckerDirection.EAST,
            CheckerDirection.SOUTH_WEST,
            CheckerDirection.SOUTH_WEST,
            CheckerDirection.NORTH_WEST);
    private Map<IPosition, ICheckerPiece> checkerPieceMap;

    public CheckerBoard() {
        checkerPieceMap = new HashMap<>();
    }

    public void init() {
        List<CheckerDirection> moves = (List<CheckerDirection>) CHECKER_DIRECTIONS.clone();
        moves.replaceAll(new UnaryOperator<CheckerDirection>() {
            @Override
            public CheckerDirection apply(CheckerDirection checkerDirection) {
                return checkerDirection.opposite();
            }
        });
        fillInBorder();
        addTeamPieces(TeamColor.BLACK, Position.newInstance(0, 0, 0), CHECKER_DIRECTIONS);
        addTeamPieces(TeamColor.GREEN, Position.newInstance(0, 9, 9), CHECKER_DIRECTIONS);
        addTeamPieces(TeamColor.BLUE, Position.newInstance(9, 9, 0), CHECKER_DIRECTIONS);
        addTeamPieces(TeamColor.RED, Position.newInstance(8, 7, -1), moves);
        addTeamPieces(TeamColor.WHITE, Position.newInstance(-1, 7, 8), moves);
        addTeamPieces(TeamColor.YELLOW, Position.newInstance(8, 16, 8), moves);

    }

    private void addTeamPieces(TeamColor color, IPosition initPosition, List<CheckerDirection> moves) {
        addPieces(color, initPosition);
        for (CheckerDirection move : moves) {
            initPosition = PositionHelper.simpleJumpPosition(initPosition, move);
            addPieces(color, initPosition);
        }
    }

    private void fillInBorder() {
        IPosition position = Position.newInstance(-1, -2, -1);
        IPosition position2 = Position.newInstance(-2, -4, -2);
        List<CheckerDirection> initLimitsMoves = Lists.newArrayList(CheckerDirection.NORTH_EAST, CheckerDirection.EAST, CheckerDirection.NORTH_WEST, CheckerDirection.NORTH_EAST, CheckerDirection.WEST, CheckerDirection.NORTH_WEST);
        for (CheckerDirection move : initLimitsMoves) {
            for (int i = 0; i < 5; i++) {
                position = addBorderPiece(position, move);
                position2 = addBorderPiece(position2, move);
            }
            position2 = addBorderPiece(position2, move);
        }
        for (CheckerDirection move : initLimitsMoves) {
            for (int i = 0; i < 5; i++) {
                position = addBorderPiece(position, move.opposite());
                position2 = addBorderPiece(position2, move.opposite());
            }
            position2 = addBorderPiece(position2, move.opposite());
        }
    }

    private IPosition addBorderPiece(IPosition position, CheckerDirection move) {
        position = PositionHelper.simpleJumpPosition(position, move);
        addPieces(TeamColor.FRONTIER, position);
        return position;
    }

    public IPosition move(ICheckerPiece checkerPiece, CheckerDirection direction) {
        IPosition position = checkerPiece.getPosition();
        checkerPieceMap.remove(position);
        IPosition newPosition = PositionHelper.sumPositions(position, direction.getPosition());
        ICheckerPiece piece = CheckerPiece.newInstance(newPosition, TeamColor.BLACK);
        checkerPieceMap.put(newPosition, piece);
        return newPosition;
    }

    public void addPieces(TeamColor teamColor, IPosition... positions) {
        for (IPosition position : positions) {
            if (checkerPieceMap.containsKey(position)) {
                throw new RuntimeException("Already existing piece at position " + position);
            }
            checkerPieceMap.put(position, CheckerPiece.newInstance(position, teamColor));
        }
    }

    public Map<IPosition, ICheckerPiece> getCheckerPieceMap() {
        return checkerPieceMap;
    }

    public ICheckerPiece getPiece(IPosition position) {
        return checkerPieceMap.get(position);
    }

    public List<List<CheckerDirection>> getAvailableMoves(IPosition currentPosition) {
        ArrayList<List<CheckerDirection>> toReturn = new ArrayList<>();
        if (getPiece(currentPosition) == null) {
            return toReturn;
        }

        for (CheckerDirection direction : CheckerDirection.values()) {
            IPosition simpleJumpToPosition = PositionHelper.simpleJumpPosition(currentPosition, direction);
            if (getPiece(simpleJumpToPosition) == null) {
                ArrayList<CheckerDirection> directions = new ArrayList<>();
                directions.add(direction);
                toReturn.add(directions);
            } else {
                toReturn.addAll(getAvailableJumps(currentPosition, direction));
            }
        }
        return toReturn;
    }

    private List<List<CheckerDirection>> getAvailableJumps(IPosition currentPosition, CheckerDirection direction) {
        ArrayList<List<CheckerDirection>> toReturn = new ArrayList<>();
        IPosition nextPosition = PositionHelper.jumpOverPiecePosition(currentPosition, direction);
        if (getPiece(nextPosition) == null) {
            ArrayList<CheckerDirection> directions = new ArrayList<>();
            directions.add(direction);
            toReturn.add(directions);
            toReturn.addAll(recursiveJumps(nextPosition, directions));
        }
        return toReturn;
    }

    private List<List<CheckerDirection>> recursiveJumps(IPosition currentPosition, List<CheckerDirection> previousMoves) {
        List<List<CheckerDirection>> lists = new ArrayList<>();
        CheckerDirection lastMove = previousMoves.get(previousMoves.size() - 1);
        for (CheckerDirection nextMove : CheckerDirection.values()) {
            if (!nextMove.opposite().equals(lastMove)) {
                if (isJumpableMove(currentPosition, nextMove)) {
                    List<CheckerDirection> possibleMoves = Lists.newArrayList(previousMoves);
                    possibleMoves.add(nextMove);
                    lists.add(possibleMoves);
                    lists.addAll(recursiveJumps(PositionHelper.jumpOverPiecePosition(currentPosition, nextMove), possibleMoves));
                }
            }
        }
        return lists;
    }

    private boolean isJumpableMove(IPosition currentPosition, CheckerDirection nextMove) {
        IPosition positionToJumpOver = PositionHelper.simpleJumpPosition(currentPosition, nextMove);
        IPosition nextPosition = PositionHelper.jumpOverPiecePosition(currentPosition, nextMove);
        return (getPiece(positionToJumpOver) != null) && (getPiece(nextPosition) == null);
    }

    @Override
    public String toString() {
        String s = "";
        for (IPosition iPosition : checkerPieceMap.keySet()) {
            s += new CartesianPosition(iPosition).toString() + '\n';
        }
        return s;
    }
}

