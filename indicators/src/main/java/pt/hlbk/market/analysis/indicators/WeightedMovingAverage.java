package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import pt.hlbk.market.analysis.models.Bar.Bar;

import java.util.concurrent.atomic.AtomicReference;

public class WeightedMovingAverage implements EventHandler<Bar> {

    private final CircularFifoQueue<Double> queue;
    private final AtomicReference<Double> wma;

    public WeightedMovingAverage() {
        queue = new CircularFifoQueue<>(10);
        wma = new AtomicReference<>(0d);
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        queue.add(event.getClose());
        Double previous = wma.get();

        int i = 1;
        double newWma = 0;
        for (Double ele : queue) {
            newWma = newWma + ele * i;
            i++;
        }
        newWma = newWma / (i - 1);

        wma.compareAndSet(previous, newWma);

        System.out.println("The WMA of " + queue + " is: " + newWma);
    }

    public double getValue() {
        return wma.get();
    }
}
