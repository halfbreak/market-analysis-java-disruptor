package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import org.jetbrains.annotations.NotNull;
import pt.hlbk.market.analysis.models.Bar.Bar;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

public class ExponentiallyWeightedMovingAverage implements EventHandler<Bar> {

    private final AtomicReference<Double> ewma;
    private final double ratio;

    public ExponentiallyWeightedMovingAverage() {
        ewma = new AtomicReference<>(null);
        ratio = (2. / (10 + 1));
    }

    public ExponentiallyWeightedMovingAverage(double ratio) {
        ewma = new AtomicReference<>(null);
        this.ratio = ratio;
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        if (ewma.get() == null) {
            ewma.compareAndSet(null, event.getClose());
        } else {
            Double val = ewma.updateAndGet(calculate(event));
            System.out.println("Current value of EWMA is: " + val);
        }
    }

    @NotNull
    private UnaryOperator<Double> calculate(Bar event) {
        return previousEwma -> ratio * event.getClose() + (1 - ratio) * previousEwma;
    }

    public double getValue() {
        return ewma.get() == null ? 0d : ewma.get();
    }
}
