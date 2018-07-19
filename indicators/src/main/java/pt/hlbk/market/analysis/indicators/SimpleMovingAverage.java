package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import pt.hlbk.market.analysis.models.Bar.Bar;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleMovingAverage implements EventHandler<Bar> {

    private final Queue<Double> queue;
    private final AtomicReference<Double> sma;

    public SimpleMovingAverage(final Queue<Double> queue) {
        this.queue = queue;
        sma = new AtomicReference<>(0d);
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        queue.add(event.getClose());
        if (queue.size() >= 5) {
            queue.stream().mapToDouble(value -> value).average().ifPresent(sma::set);
            System.out.println("SMA of " + queue.toString() + " : " + sma.get());
        }
    }

    public double getValue() {
        return sma.get();
    }
}
