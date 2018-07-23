package pt.hlbk.market.analysis.indicators;

import com.lmax.disruptor.EventHandler;
import pt.hlbk.market.analysis.models.Bar.Bar;

public interface Indicator extends EventHandler<Bar> {
    double getValue();
}
