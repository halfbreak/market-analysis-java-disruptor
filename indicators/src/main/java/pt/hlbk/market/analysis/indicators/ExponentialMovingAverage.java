package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class ExponentialMovingAverage implements EventHandler<Bar> {

    private static final int TIME_PERIOD = 10;
    private static final double MULTIPLIER = (2. / (TIME_PERIOD + 1));

    private CircularFifoQueue<Double> queue;
    private Double sma;
    private Double ema;

    public ExponentialMovingAverage() {
        this.queue = new CircularFifoQueue<>(10);
        sma = null;
        ema = null;
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        if (queue != null) {
            queue.add(event.getClose());
            if (queue.size() >= TIME_PERIOD) {
                if (sma == null) {
                    sma = calculateSMA();
                    System.out.println("SMA of " + queue.toString() + " : " + sma);
                }
                double newVal = update(event);
                System.out.println("EMA of " + queue.toString() + " : " + newVal);

                queue = null;
            }
        } else {
            double newVal = update(event);
            System.out.println("Current EMA is : " + newVal);
        }
    }

    private Double update(Bar event) {
        Double previousEma = ema;
        if (previousEma == null) {
            previousEma = sma;
        }
        ema = calculateEMA(event.getClose(), previousEma);
        return ema;
    }

    private double calculateEMA(double close, Double previousEma) {
        return (close - previousEma) * MULTIPLIER + previousEma;
    }

    private double calculateSMA() {
        return queue.stream()
                .mapToDouble(value -> value)
                .average()
                .getAsDouble();
    }

    public double getValue() {
        return ema == null ? 0d : ema;
    }
}
