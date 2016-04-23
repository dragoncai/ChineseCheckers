package dragoncai.chinese.checkers;

import org.fest.assertions.Assertions;
import org.junit.Test;

/**
 * Created by SAC D'AUBER on 16/04/2016.
 */
public class PositionHelperTest {
    private static IPosition position1 = Position.newInstance(1,1,1);
    private static IPosition position2 = Position.newInstance(0,-5,1);
    private static IPosition position3 = Position.newInstance(3,2,-1);

    @Test
    public void testSumPositionsShouldHaveProduceANewPositionWithTheTotalOfCoordinates() throws Exception {
        IPosition expectedPosition = Position.newInstance(4,-2,1);
        IPosition actualPosition = PositionHelper.sumPositions(position1, position2, position3);
        Assertions.assertThat(actualPosition).isEqualTo(expectedPosition);
    }
}