package pt.hlbk.market.analysis.indicators;

import org.junit.Assert;
import org.junit.Test;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class ExponentiallyWeightedMovingAverageTest {

    @Test
    public void shouldCalculateEWMA() {
        ExponentiallyWeightedMovingAverage ewma = new ExponentiallyWeightedMovingAverage();
        Assert.assertEquals(0, ewma.getValue(), 0);

        ewma.onEvent(create(1), 0, false);
        Assert.assertEquals(1.0, ewma.getValue(), 0);

        ewma.onEvent(create(2), 0, false);
        Assert.assertEquals(1.1818181818181817, ewma.getValue(), 0);

        ewma.onEvent(create(3), 0, false);
        Assert.assertEquals(1.5123966942148757, ewma.getValue(), 0);
    }

    private static Bar create(double closeValue) {
        return new Bar(0, 0, 0, closeValue);
    }

}