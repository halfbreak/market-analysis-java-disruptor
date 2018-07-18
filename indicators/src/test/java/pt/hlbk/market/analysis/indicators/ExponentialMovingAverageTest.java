package pt.hlbk.market.analysis.indicators;

import org.junit.Assert;
import org.junit.Test;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class ExponentialMovingAverageTest {

    @Test
    public void shouldCalculateEMA() {

        ExponentialMovingAverage sma = new ExponentialMovingAverage();
        sma.onEvent(create(1), 1, false);
        sma.onEvent(create(2), 1, false);
        sma.onEvent(create(3), 1, false);
        sma.onEvent(create(4), 1, false);
        sma.onEvent(create(5), 1, false);
        sma.onEvent(create(6), 1, false);
        sma.onEvent(create(7), 1, false);
        sma.onEvent(create(8), 1, false);
        sma.onEvent(create(9), 1, false);
        sma.onEvent(create(10), 1, false);
        Assert.assertEquals(0, sma.getValue(), 0);

        sma.onEvent(create(11), 1, false);
        Assert.assertEquals(7.318181818181818, sma.getValue(), 0);

        sma.onEvent(create(12), 1, false);
        Assert.assertEquals(8.169421487603305, sma.getValue(), 0);
    }

    private static Bar create(double closeValue) {
        return new Bar(0, 0, 0, closeValue);
    }
}