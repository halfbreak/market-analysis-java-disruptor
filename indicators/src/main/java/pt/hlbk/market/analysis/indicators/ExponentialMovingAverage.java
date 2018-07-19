package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import pt.hlbk.market.analysis.models.Bar.Bar;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

public class ExponentialMovingAverage implements EventHandler<Bar> {

    private static final int TIME_PERIOD = 10;
    private static final double MULTIPLIER = (2. / (TIME_PERIOD + 1));

    private final Queue<Double> queue;
    private final AtomicReference<Double> sma;
    private final AtomicReference<Double> ema;

    public ExponentialMovingAverage(final Queue<Double> queue) {
        this.queue = queue;
        sma = new AtomicReference<>(null);
        ema = new AtomicReference<>(null);
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        queue.add(event.getClose());
        if (queue.size() >= TIME_PERIOD) {
            if (sma.compareAndSet(null, calculateSMA())) {
                System.out.println("SMA of " + queue.toString() + " : " + sma.get());
            }
            
            double newVal = ema.updateAndGet(val -> {
                Double previousEma = val;
                if (previousEma == null) {
                    previousEma = sma.get();
                }
                return calculateEMA(event.getClose(), previousEma);
            });

            System.out.println("EMA of " + queue.toString() + " : " + newVal);
        }
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
        return ema.get() == null ? 0d : ema.get();
    }
}
