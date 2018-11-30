package customer;

import discount.Discount;
import org.junit.jupiter.api.Test;
import payment_method.IPaymentMethod;
import product.IProductsReserve;
import product.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Customer customer = new Customer(
            3,
            ICustomer.CustomerType.Child,
            IPaymentMethod.Method.Card,
            new BigDecimal(100),
            new BigDecimal(150),
            new BigDecimal(60)
    );


//    private Product product = new Product(
//            4,
//            "Milk",
//            new Discount(0),
//            new BigDecimal(50),
//            true,
//            IProductsReserve.ProductMeasure.PIECES);

    @Test
    void getType() {
        //assertEquals(customer.getType(), ICustomer.CustomerType.Child);
    }
//
//    @Test
//    void getId() {
//    }
//
//    @Test
//    void getCash() {
//    }
//
//    @Test
//    void getCardCash() {
//    }
//
//    @Test
//    void getBonuses() {
//    }
//
//    @Test
//    void setBonuses() {
//    }
//
//    @Test
//    void getPaymentMethod() {
//    }
//
//    @Test
//    void getBasket() {
//    }
//
//    @Test
//    void addProductToBasket() {
//    }
//
//    @Test
//    void removeProductFromBasket() {
//    }
//
//    @Test
//    void clearBasket() {
//    }
//
//    @Test
//    void issetProductsInBasket() {
//    }
//
//    @Test
//    void isAdult() {
//    }
//
//    @Test
//    void isRetired() {
//    }
//
//    @Test
//    void updateCash() {
//    }
//
//    @Test
//    void updateCardCash() {
//    }
//
//    @Test
//    void updateBonuses() {
//    }
}