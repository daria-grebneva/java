package customer;

import basket.Basket;
import payment_method.IPaymentMethod;

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
    BigDecimal getBonuses();
    void setBonuses(BigDecimal bonuses);

    IPaymentMethod.Method getPaymentMethod();

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
