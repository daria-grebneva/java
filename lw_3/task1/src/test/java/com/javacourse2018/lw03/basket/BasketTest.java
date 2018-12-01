package com.javacourse2018.lw03.basket;

import com.javacourse2018.lw03.discount.Discount;
import com.javacourse2018.lw03.product.IProductsReserve;
import com.javacourse2018.lw03.product.Product;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BasketTest {
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

  @Test
  public void addProduct() {
    Basket basket1 = new Basket();
    Basket basket2 = new Basket();
    basket1.addProduct(product.getProductId(), 1);
    basket2.addProduct(product.getProductId(), 1);
    Assert.assertEquals(basket1.getBasketContent(), basket2.getBasketContent());
  }

  @Test
  public void removeProduct() {
    Basket basket1 = new Basket();
    Basket basket2 = new Basket();

    basket1.addProduct(product.getProductId(), 1);
    basket1.removeProduct(product.getProductId());
    Assert.assertEquals(basket1.getBasketContent(), basket2.getBasketContent());
  }

  @Test
  public void clearBasket() {
    Basket basket1 = new Basket();
    Basket basket2 = new Basket();

    basket1.addProduct(product.getProductId(), 1);
    basket1.addProduct(product1.getProductId(), 4);
    basket1.addProduct(product2.getProductId(), 5);
    basket1.clearBasket();

    Assert.assertEquals(basket1.getBasketContent(), basket2.getBasketContent());
  }

  @Test
  public void getBasketSize() {
    Basket basket1 = new Basket();
    basket1.addProduct(product.getProductId(), 1);
    basket1.addProduct(product1.getProductId(), 4);
    basket1.addProduct(product2.getProductId(), 5);
    Assert.assertEquals(basket1.getBasketSize(), 3);
  }

  @Test
  public void getBasketContent() {
    Basket basket1 = new Basket();
    Basket basket2 = new Basket();
    basket1.addProduct(product.getProductId(), 1);
    basket1.addProduct(product1.getProductId(), 4);
    basket1.addProduct(product2.getProductId(), 5);
    basket2.addProduct(product.getProductId(), 1);
    basket2.addProduct(product1.getProductId(), 4);
    basket2.addProduct(product2.getProductId(), 5);
    Assert.assertEquals(basket1.getBasketContent(), basket2.getBasketContent());
  }
}