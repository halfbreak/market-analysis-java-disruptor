package pt.hlbk.market.analysis.timesource;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.Test;
import pt.hlbk.market.analysis.models.Bar.Bar;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

public class WindowedTimeSourceTest {

    @Test(timeout = 1000)
    public void shouldAddAndMove() {
        AtomicBoolean called = new AtomicBoolean(false);

        BarEventFactory factory = new BarEventFactory();
        int bufferSize = 2048;
        Disruptor<Bar> disruptor = new Disruptor<>(factory, bufferSize, (ThreadFactory) Thread::new,
                ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith((EventHandler<Bar>) (event, sequence, endOfBatch) -> called.set(true));
        disruptor.start();

        TimeSource timeSource = new WindowedTimeSource(disruptor.getRingBuffer());
        timeSource.add(0, 0, 0, 0);

        while (!called.get()) {}
    }
}