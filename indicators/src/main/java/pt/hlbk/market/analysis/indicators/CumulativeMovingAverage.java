package pt.hlbk.market.analysis.indicators;

import pt.hlbk.market.analysis.models.Bar.Bar;

public class CumulativeMovingAverage implements Indicator {

    private double cma;
    private long number;

    public CumulativeMovingAverage() {
        cma = 0d;
        number = 0;
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        cma = calcNewVal(event.getClose(), number++, cma);
        System.out.println("Current value of CMA is: " + cma);
    }

    private double calcNewVal(final double close, final long numberOfEntries, final double currentCMA) {
        return (close + numberOfEntries * currentCMA) / (numberOfEntries + 1);
    }

    @Override
    public double getValue() {
        return cma;
    }
}
