package pt.hlbk.market.analysis.timesource;

import com.lmax.disruptor.RingBuffer;
import pt.hlbk.market.analysis.models.Bar.Bar;

public class WindowedTimeSource implements TimeSource {

    private final RingBuffer<Bar> ringBuffer;

    public WindowedTimeSource(RingBuffer<Bar> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    @Override
    public void add(double open, double low, double high, double close) {
        long sequence = ringBuffer.next();
        try {
            Bar event = ringBuffer.get(sequence);
            event.setOpen(open);
            event.setLow(low);
            event.setHigh(high);
            event.setClose(close);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
