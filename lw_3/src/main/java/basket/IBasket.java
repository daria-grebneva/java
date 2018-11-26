package basket;

import java.util.Map;

public interface IBasket {

    void addProduct(int productId, int count);
    void removeProduct(int productId);
    void clearBasket();
    int getBasketSize();
    //TODO:: поменять map на другую структуру данных
    Map<Integer, Integer> getBasketContent();
    int[] arrToInt();

}
