package basket;

import discount.Discount;
import product.IProductsReserve;
import product.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    private Product product = new Product(
            4,
            "Milk",
            new Discount(0),
            new BigDecimal(50),
            true,
            IProductsReserve.ProductMeasure.PIECES);

    private Product product1 = new Product(
            5,
            "Chocolate",
            new Discount(0),
            new BigDecimal(50),
            true,
            IProductsReserve.ProductMeasure.PIECES);

    private Product product2 = new Product(
            6,
            "Bread",
            new Discount(0),
            new BigDecimal(50),
            true,
            IProductsReserve.ProductMeasure.PIECES);

    @org.junit.jupiter.api.Test
    void addProduct() {
        Basket basket1 = new Basket();
        Basket basket2 = new Basket();
        basket1.addProduct(product.getProductId(), 1);
        basket2.addProduct(product.getProductId(), 1);
        assertEquals(basket1.getBasketContent(), basket2.getBasketContent());
    }

    @org.junit.jupiter.api.Test
    void removeProduct() {
        Basket basket1 = new Basket();
        Basket basket2 = new Basket();

        basket1.addProduct(product.getProductId(), 1);
        basket1.removeProduct(product.getProductId());
        assertEquals(basket1.getBasketContent(), basket2.getBasketContent());
    }

    @org.junit.jupiter.api.Test
    void clearBasket() {
        Basket basket1 = new Basket();
        Basket basket2 = new Basket();

        basket1.addProduct(product.getProductId(), 1);
        basket1.addProduct(product1.getProductId(), 4);
        basket1.addProduct(product2.getProductId(), 5);
        basket1.clearBasket();

        assertEquals(basket1.getBasketContent(), basket2.getBasketContent());
    }

    @org.junit.jupiter.api.Test
    void getBasketSize() {
        Basket basket1 = new Basket();
        basket1.addProduct(product.getProductId(), 1);
        basket1.addProduct(product1.getProductId(), 4);
        basket1.addProduct(product2.getProductId(), 5);
        assertEquals(basket1.getBasketSize(), 3);
    }

    @org.junit.jupiter.api.Test
    void getBasketContent() {
        Basket basket1 = new Basket();
        Basket basket2 = new Basket();
        basket1.addProduct(product.getProductId(), 1);
        basket1.addProduct(product1.getProductId(), 4);
        basket1.addProduct(product2.getProductId(), 5);
        basket2.addProduct(product.getProductId(), 1);
        basket2.addProduct(product1.getProductId(), 4);
        basket2.addProduct(product2.getProductId(), 5);
        assertEquals(basket1.getBasketContent(), basket2.getBasketContent());
    }
}