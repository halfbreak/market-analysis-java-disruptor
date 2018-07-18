package pt.hlbk.market.analysis.indicators;

import org.junit.Assert;
import org.junit.Test;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class SimpleMovingAverageTest {

    @Test
    public void shouldCalculateSMA() {

        SimpleMovingAverage sma = new SimpleMovingAverage();
        sma.onEvent(create(1), 1, false);
        sma.onEvent(create(2), 1, false);
        sma.onEvent(create(3), 1, false);
        sma.onEvent(create(4), 1, false);
        sma.onEvent(create(5), 1, false);
        Assert.assertEquals(0, sma.getValue(), 0);

        sma.onEvent(create(1), 1, false);
        Assert.assertEquals(6.5, sma.getValue(), 0);
    }

    private static Bar create(double closeValue) {
        return new Bar(0, 0, 0, closeValue);
    }
}