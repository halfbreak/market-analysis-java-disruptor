package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import pt.hlbk.market.analysis.models.Bar.Bar;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicReference;

public class SimpleMovingAverage implements EventHandler<Bar> {

    private final ConcurrentLinkedDeque<Double> queue;
    private final AtomicReference<Double> sma;

    public SimpleMovingAverage() {
        queue = new ConcurrentLinkedDeque<>();
        sma = new AtomicReference<>(0d);
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        if (queue.size() >= 5) {
            queue.removeLast();
            queue.push(event.getClose());
            queue.stream().mapToDouble(value -> value).average().ifPresent(sma::set);
            System.out.println("SMA of " + queue.toString() + " : " + sma.get());
        } else {
            queue.push(event.getClose());
        }
    }

    public double getValue() {
        return sma.get();
    }
}
