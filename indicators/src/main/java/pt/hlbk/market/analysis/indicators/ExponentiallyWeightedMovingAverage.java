package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import org.jetbrains.annotations.NotNull;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class ExponentiallyWeightedMovingAverage implements EventHandler<Bar> {

    private Double ewma;
    private final double ratio;

    public ExponentiallyWeightedMovingAverage() {
        ewma = null;
        ratio = (2. / (10 + 1));
    }

    public ExponentiallyWeightedMovingAverage(double ratio) {
        ewma = null;
        this.ratio = ratio;
    }

    @Override
    public void onEvent(Bar event, long sequence, boolean endOfBatch) {
        if (ewma == null) {
            ewma = event.getClose();
        } else {
            ewma = calculate(event, ewma);
            Double val = ewma;
            System.out.println("Current value of EWMA is: " + val);
        }
    }

    @NotNull
    private Double calculate(Bar event, Double previousEwma) {
        return ratio * event.getClose() + (1 - ratio) * previousEwma;
    }

    public double getValue() {
        return ewma == null ? 0d : ewma;
    }
}
