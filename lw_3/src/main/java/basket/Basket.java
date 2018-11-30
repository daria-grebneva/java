package basket;

import java.util.*;

public class Basket implements IBasket {

    private Map<Integer, Integer> _products = new HashMap<Integer, Integer>();

    public void addProduct(int productId, int count) {
        this._products.put(productId, count);
    }

    public void removeProduct(int productId) {
        this._products.remove(productId);
    }

    public void clearBasket() {
        this._products.clear();
    }

    public int getBasketSize() {
        return this._products.size();
    }

    public Map<Integer, Integer> getBasketContent() {
        return _products;
    }

}
