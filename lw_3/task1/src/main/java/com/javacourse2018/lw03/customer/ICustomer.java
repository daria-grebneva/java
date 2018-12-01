package com.javacourse2018.lw03.customer;

import com.javacourse2018.lw03.basket.Basket;
import com.javacourse2018.lw03.payment_method.Method;

import java.math.BigDecimal;

public interface ICustomer {

    enum CustomerType {
        Child,
        Adult,
        Retired
    }

    CustomerType getType();
    Integer getId();

    BigDecimal getCash();
    BigDecimal getCardCash();
    BigDecimal getBonuses();
    void setBonuses(BigDecimal bonuses);

    Method getPaymentMethod();

    Basket getBasket();
    void addProductToBasket(int productId, int count);
    void removeProductFromBasket(int productId);
    void clearBasket();
    boolean issetProductsInBasket();

    boolean isAdult();
    boolean isRetired();

    void updateCash(BigDecimal value);
    void updateCardCash(BigDecimal value);
    void updateBonuses(BigDecimal value);

    static CustomerType getCustomTypeByCode(int code)
    {
        return CustomerType.values()[code];
    }
}
