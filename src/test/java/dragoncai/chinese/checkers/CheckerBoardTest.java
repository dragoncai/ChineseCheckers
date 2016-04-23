package dragoncai.chinese.checkers;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;


/**
 * Created by SAC D'AUBER on 23/04/2016.
 */
public class CheckerBoardTest {
    private CheckerBoard checkerBoard;

    @Before
    public void setUp() throws Exception {
        checkerBoard = new CheckerBoard();
        checkerBoard.init();
    }

    @Test
    public void name() throws Exception {
        IPosition iPosition = Position.newInstance(0, 0, 0);
        checkerBoard.addPieces(TeamColor.BLACK, iPosition);
        ICheckerPiece checkerPiece = checkerBoard.getPiece(iPosition);
        IPosition position = checkerBoard.move(checkerPiece, CheckerDirection.NORTH_EAST);
        assertThat(position).isEqualTo(Position.newInstance(0, 1, 1));
        assertThat(checkerBoard.getCheckerPieceMap().get(position)).isNotNull();
        assertThat(checkerBoard.getCheckerPieceMap().get(position).getPosition()).isEqualTo(position);
    }

    @Test
    public void testDirectAvailableMoves() throws Exception {
        checkerBoard.addPieces(TeamColor.BLACK, Position.newInstance(0, 1, 1), Position.newInstance(1, 2, 1),
                Position.newInstance(1, 3, 2), Position.newInstance(2, 4, 2), Position.newInstance(2, 5, 3));
        List<List<CheckerDirection>> availableMoves = checkerBoard.getAvailableMoves(Position.newInstance(1, 2, 1));
        assertThat(availableMoves).contains(Lists.newArrayList(CheckerDirection.SOUTH_WEST)
                , Lists.newArrayList(CheckerDirection.NORTH_WEST)
                , Lists.newArrayList(CheckerDirection.EAST)
                , Lists.newArrayList(CheckerDirection.WEST));
    }

    @Test
    public void testOneJumpAvailableMoves() throws Exception {
        checkerBoard.addPieces(TeamColor.BLACK, Position.newInstance(0, 1, 1), Position.newInstance(1, 2, 1),
                Position.newInstance(1, 3, 2), Position.newInstance(2, 4, 2), Position.newInstance(2, 5, 3));
        List<List<CheckerDirection>> availableMoves = checkerBoard.getAvailableMoves(Position.newInstance(1, 2, 1));
        assertThat(availableMoves).contains(Lists.newArrayList(CheckerDirection.NORTH_EAST));
    }

    @Test
    public void testAllJumpAvailableMoves() throws Exception {
        checkerBoard.addPieces(TeamColor.BLACK, Position.newInstance(0, 1, 1), Position.newInstance(1, 2, 1),
                Position.newInstance(1, 3, 2), Position.newInstance(2, 4, 2), Position.newInstance(2, 5, 3));
        List<List<CheckerDirection>> availableMoves = checkerBoard.getAvailableMoves(Position.newInstance(1, 2, 1));
        assertThat(availableMoves).contains(Lists.newArrayList(CheckerDirection.NORTH_EAST)
                , Lists.newArrayList(CheckerDirection.NORTH_EAST, CheckerDirection.NORTH_WEST)
                , Lists.newArrayList(CheckerDirection.NORTH_EAST, CheckerDirection.NORTH_WEST)
                , Lists.newArrayList(CheckerDirection.NORTH_EAST, CheckerDirection.WEST));
    }

    @Test
    public void testAllAvailableMoves() throws Exception {
        checkerBoard.addPieces(TeamColor.BLACK, Position.newInstance(0, 1, 1), Position.newInstance(1, 2, 1),
                Position.newInstance(1, 3, 2), Position.newInstance(2, 4, 2), Position.newInstance(2, 5, 3));
        List<List<CheckerDirection>> availableMoves = checkerBoard.getAvailableMoves(Position.newInstance(1, 2, 1));
        assertThat(availableMoves).containsOnly(Lists.newArrayList(CheckerDirection.SOUTH_WEST)
                , Lists.newArrayList(CheckerDirection.NORTH_WEST)
                , Lists.newArrayList(CheckerDirection.EAST)
                , Lists.newArrayList(CheckerDirection.WEST)
                , Lists.newArrayList(CheckerDirection.NORTH_EAST)
                , Lists.newArrayList(CheckerDirection.NORTH_EAST, CheckerDirection.NORTH_WEST)
                , Lists.newArrayList(CheckerDirection.NORTH_EAST, CheckerDirection.WEST));
    }

    @Test
    public void platform() throws Exception {
        System.out.println(checkerBoard.toString());
    }
}
