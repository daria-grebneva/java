package com.javacourse2018.lw03.basket;

import java.util.Map;

public interface IBasket {

    void addProduct(int productId, int count);
    void removeProduct(int productId);
    void clearBasket();
    int getBasketSize();
    Map<Integer, Integer> getBasketContent();

}
