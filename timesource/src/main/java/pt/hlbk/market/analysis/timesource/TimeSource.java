package pt.hlbk.market.analysis.timesource;

public interface TimeSource {
    void add(final double open, final double low, final double high, final double close);
}
