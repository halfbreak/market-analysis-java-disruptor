package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class WeightedMovingAverage implements EventHandler<Bar> {

    private final CircularFifoQueue<Double> queue;
    private Double wma;

    public WeightedMovingAverage() {
        queue = new CircularFifoQueue<>(10);
        wma = 0d;
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        queue.add(event.getClose());

        int i = 1;
        double newWma = 0;
        for (Double ele : queue) {
            newWma = newWma + ele * i;
            i++;
        }
        newWma = newWma / (i - 1);

        wma = newWma;

        System.out.println("The WMA of " + queue + " is: " + newWma);
    }

    public double getValue() {
        return wma;
    }
}
