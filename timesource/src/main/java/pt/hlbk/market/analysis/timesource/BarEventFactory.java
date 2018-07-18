package pt.hlbk.market.analysis.timesource;

import com.lmax.disruptor.EventFactory;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class BarEventFactory implements EventFactory<Bar> {

    @Override
    public Bar newInstance() {
        return new Bar();
    }
}
