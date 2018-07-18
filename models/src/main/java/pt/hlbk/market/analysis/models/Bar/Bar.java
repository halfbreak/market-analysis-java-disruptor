package pt.hlbk.market.analysis.models.Bar;

public class Bar {
    private double open;
    private double low;
    private double high;
    private double close;

    public Bar() {
    }

    public Bar(double open, double low, double high, double close) {
        this.open = open;
        this.low = low;
        this.high = high;
        this.close = close;
    }

    public double getOpen() {
        return open;
    }

    public double getLow() {
        return low;
    }

    public double getHigh() {
        return high;
    }

    public double getClose() {
        return close;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setClose(double close) {
        this.close = close;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "open=" + open +
                ", low=" + low +
                ", high=" + high +
                ", close=" + close +
                '}';
    }
}
