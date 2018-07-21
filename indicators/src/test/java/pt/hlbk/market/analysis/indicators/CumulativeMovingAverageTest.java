package pt.hlbk.market.analysis.indicators;

import org.junit.Assert;
import org.junit.Test;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class CumulativeMovingAverageTest {

    @Test
    public void shouldCalcCMA() {
        CumulativeMovingAverage cma = new CumulativeMovingAverage();
        Assert.assertEquals(0, cma.getVal(), 0);

        cma.onEvent(create(1), 1, false);
        Assert.assertEquals(1, cma.getVal(), 0);

        cma.onEvent(create(3), 1, false);
        Assert.assertEquals(2, cma.getVal(), 0);

        cma.onEvent(create(5), 1, false);
        Assert.assertEquals(3, cma.getVal(), 0);
    }

    private static Bar create(double closeValue) {
        return new Bar(0, 0, 0, closeValue);
    }
}