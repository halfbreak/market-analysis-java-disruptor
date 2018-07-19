package pt.hlbk.market.analysis.indicators;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.Queues;
import org.junit.Assert;
import org.junit.Test;
import pt.hlbk.market.analysis.models.Bar.Bar;

import java.util.Queue;

public class SimpleMovingAverageTest {

    @Test
    public void shouldCalculateSMA() {
        final EvictingQueue<Double> q = EvictingQueue.create(5);
        final Queue<Double> queue = Queues.synchronizedQueue(q);
        final SimpleMovingAverage sma = new SimpleMovingAverage(queue);
        sma.onEvent(create(1), 1, false);
        sma.onEvent(create(2), 1, false);
        sma.onEvent(create(3), 1, false);
        sma.onEvent(create(4), 1, false);
        Assert.assertEquals(0, sma.getValue(), 0);

        sma.onEvent(create(5), 1, false);
        Assert.assertEquals(3.0, sma.getValue(), 0);

        sma.onEvent(create(6), 1, false);
        Assert.assertEquals(4.0, sma.getValue(), 0);
    }

    private static Bar create(double closeValue) {
        return new Bar(0, 0, 0, closeValue);
    }
}