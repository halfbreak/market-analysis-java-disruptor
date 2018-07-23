package pt.hlbk.market.analysis.indicators;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class SimpleMovingAverage implements Indicator {

    private final CircularFifoQueue<Double> queue;
    private double sma;

    public SimpleMovingAverage() {
        this.queue = new CircularFifoQueue<>(5);
        sma = 0d;
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        queue.add(event.getClose());
        if (queue.size() >= 5) {
            queue.stream().mapToDouble(value -> value).average().ifPresent(value -> sma = value);
            System.out.println("SMA of " + queue.toString() + " : " + sma);
        }
    }

    @Override
    public double getValue() {
        return sma;
    }
}
