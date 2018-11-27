package customer;

import basket.Basket;
import payment_method.PaymentMethod;

import java.math.BigDecimal;

public interface ICustomer {

    public enum CustomerType {
        Child,
        Adult,
        Retired
    }

    CustomerType getType();
    Integer getId();

    BigDecimal getCash();
    BigDecimal getCardCash();
    int getBonuses();
    void setBonuses(int bonuses);

    PaymentMethod.Method getPaymentMethod();

    Basket getBasket();
    void addProductToBasket(int productId, int count);
    void removeProductFromBasket(int productId);
    void clearBasket();
    boolean issetProductsInBasket();

    boolean isAdult();
    boolean isRetired();

    void updateCash(BigDecimal value);
    void updateCardCash(BigDecimal value);
    void updateBonuses(int value);

    static CustomerType getCustomTypeByCode(int code)
    {
        return CustomerType.values()[code];
    }
}
