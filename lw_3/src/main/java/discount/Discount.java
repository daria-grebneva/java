package discount;

public class Discount implements IDiscount {
    private int _percent;

    public Discount(int percent) {
        this._percent = percent;
    }

    public int getPercent() {
        return _percent;
    }
}
