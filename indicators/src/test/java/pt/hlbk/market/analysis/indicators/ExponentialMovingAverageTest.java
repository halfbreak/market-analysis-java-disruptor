package pt.hlbk.market.analysis.indicators;

import org.junit.Assert;
import org.junit.Test;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class ExponentialMovingAverageTest {

    @Test
    public void shouldCalculateEMA() {
        final ExponentialMovingAverage ema = new ExponentialMovingAverage();
        ema.onEvent(create(1), 1, false);
        ema.onEvent(create(2), 1, false);
        ema.onEvent(create(3), 1, false);
        ema.onEvent(create(4), 1, false);
        ema.onEvent(create(5), 1, false);
        ema.onEvent(create(6), 1, false);
        ema.onEvent(create(7), 1, false);
        ema.onEvent(create(8), 1, false);
        ema.onEvent(create(9), 1, false);
        Assert.assertEquals(0, ema.getValue(), 0);

        ema.onEvent(create(10), 1, false);
        Assert.assertEquals(6.318181818181818, ema.getValue(), 0);

        ema.onEvent(create(11), 1, false);
        Assert.assertEquals(7.169421487603306, ema.getValue(), 0);
    }

    private static Bar create(double closeValue) {
        return new Bar(0, 0, 0, closeValue);
    }
}