package com.javacourse2018.lw03.customer;

import com.javacourse2018.lw03.basket.Basket;

import com.javacourse2018.lw03.payment_method.Method;
import java.math.BigDecimal;

public class Customer implements ICustomer {

    private int _id;
    private CustomerType _type;
    private Basket _basket;
    private Method _paymentMethod;
    private BigDecimal _cash;
    private BigDecimal _cardCash;
    private BigDecimal _bonuses;

    public Customer(int id, CustomerType type, Method paymentMethod, BigDecimal cash, BigDecimal cardCash, BigDecimal bonuses){
        this._id = id;
        this._type = type;
        this._basket = new Basket();
        this._paymentMethod = paymentMethod;
        this._cash = cash;
        this._cardCash = cardCash;
        this._bonuses = bonuses;
    }

    public CustomerType getType()
    {
        return this._type;
    }

    public Integer getId()
    {
        return _id;
    }

    public BigDecimal getCash() {
        return this._cash;
    }

    public BigDecimal getCardCash() {
        return this._cardCash;
    }

    public BigDecimal getBonuses() {
        return this._bonuses;
    }

    public void setBonuses(BigDecimal bonuses) {
        this._bonuses = (this._bonuses).add(bonuses);
    }

    public Method getPaymentMethod()
    {
        return this._paymentMethod;
    }

    public Basket getBasket() {
        return this._basket;
    }

    public void addProductToBasket(int productId, int count) {
        this._basket.addProduct(productId, count);
    }

    public void removeProductFromBasket(int productId) {
        this._basket.removeProduct(productId);
    }

    public void clearBasket() {
        this._basket.clearBasket();
    }

    public boolean issetProductsInBasket() {
        return this._basket.getBasketSize() > 0;
    }

    public boolean isAdult() {
        return this._type == CustomerType.Adult || this._type == CustomerType.Retired;
    }

    public boolean isRetired() {
        return this._type == CustomerType.Retired;
    }

    public void updateCash(BigDecimal value) {
        this._cash = value;
    }

    public void updateCardCash(BigDecimal value) {
        this._cardCash = value;
    }

    public void updateBonuses(BigDecimal value) {
        this._bonuses = value;
    }

}
