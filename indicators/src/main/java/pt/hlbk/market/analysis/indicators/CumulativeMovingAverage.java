package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import pt.hlbk.market.analysis.models.Bar.Bar;

import java.util.concurrent.atomic.AtomicLong;

public class CumulativeMovingAverage implements EventHandler<Bar> {

    private double cma;
    private final AtomicLong number;

    public CumulativeMovingAverage() {
        cma = 0d;
        number = new AtomicLong(0);
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        cma = calcNewVal(event.getClose(), number.getAndIncrement(), cma);
        System.out.println("Current value of CMA is: " + cma);
    }

    private double calcNewVal(final double close, final long numberOfEntries, final double currentCMA) {
        return (close + numberOfEntries * currentCMA) / (numberOfEntries + 1);
    }

    public double getVal() {
        return cma;
    }
}
