package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import pt.hlbk.market.analysis.models.Bar.Bar;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class CumulativeMovingAverage implements EventHandler<Bar> {

    private final AtomicReference<Double> cma;
    private final AtomicLong number;

    public CumulativeMovingAverage() {
        cma = new AtomicReference<>(0d);
        number = new AtomicLong(0);
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        Double val = cma.updateAndGet(currentCMA ->
                calcNewVal(event.getClose(), number.getAndIncrement(), currentCMA));
        System.out.println("Current value of CMA is: " + val);
    }

    private double calcNewVal(final double close, final long numberOfEntries, final double currentCMA) {
        return (close + numberOfEntries * currentCMA) / (numberOfEntries + 1);
    }

    public double getVal() {
        return cma.get();
    }
}
