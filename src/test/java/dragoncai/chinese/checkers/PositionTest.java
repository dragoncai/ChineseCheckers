package dragoncai.chinese.checkers;

import org.fest.assertions.Assertions;

/**
 * Created by SAC D'AUBER on 16/04/2016.
 */
public class PositionTest {
    @org.junit.Test
    public void testBuilder() throws Exception {
        int xLeft = 1;
        int xRight = 1;
        int y = 1;
        IPosition position = Position.newInstance(xLeft, xRight, y);
        Assertions.assertThat(position.getXLeft()).isEqualTo(xLeft);
        Assertions.assertThat(position.getXRight()).isEqualTo(xRight);
        Assertions.assertThat(position.getY()).isEqualTo(y);
    }
}