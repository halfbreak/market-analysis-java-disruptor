package pt.hlbk.market.analysis.indicators;

import org.junit.Assert;
import org.junit.Test;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class WeightedMovingAverageTest {

    @Test
    public void shouldCalculateWMA() {
        WeightedMovingAverage wma = new WeightedMovingAverage();
        Assert.assertEquals(0, wma.getValue(), 0);

        wma.onEvent(create(1), 1, false);
        Assert.assertEquals(1, wma.getValue(), 0);

        wma.onEvent(create(2), 1, false);
        Assert.assertEquals(2.5, wma.getValue(), 0);
    }

    private static Bar create(double closeValue) {
        return new Bar(0, 0, 0, closeValue);
    }
}