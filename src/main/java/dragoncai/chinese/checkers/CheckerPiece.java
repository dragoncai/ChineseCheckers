package dragoncai.chinese.checkers;

/**
 * Created by SAC D'AUBER on 16/04/2016.
 */
public class CheckerPiece implements ICheckerPiece {
    private final IPosition position;
    private final TeamColor teamColor;

    private CheckerPiece(IPosition position, TeamColor teamColor) {
        this.position = position;
        this.teamColor = teamColor;
    }

    public static ICheckerPiece newInstance(IPosition position, TeamColor teamColor) {
        return new CheckerPiece(position, teamColor);
    }

    @Override
    public IPosition getPosition() {
        return position;
    }

    @Override
    public TeamColor getTeamColor() {
        return teamColor;
    }
}
