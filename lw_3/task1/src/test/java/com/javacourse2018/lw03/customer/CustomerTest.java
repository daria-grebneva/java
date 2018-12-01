package com.javacourse2018.lw03.customer;

import com.javacourse2018.lw03.basket.Basket;
import org.junit.Test;
import com.javacourse2018.lw03.discount.Discount;
import com.javacourse2018.lw03.payment_method.Method;
import com.javacourse2018.lw03.product.IProductsReserve;
import com.javacourse2018.lw03.product.Product;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CustomerTest {
  private Customer customer = new Customer(
          3,
          ICustomer.CustomerType.Child,
          Method.Card,
          new BigDecimal(100),
          new BigDecimal(150),
          new BigDecimal(60)
  );

  private Product product = new Product(
          4,
          "Milk",
          new Discount(0),
          new BigDecimal(50),
          true,

          IProductsReserve.ProductMeasure.PIECES);

  @Test
  public void getType() {
    assertEquals(customer.getType(), ICustomer.CustomerType.Child);
  }

  @Test
  public void getId() {
    int n = customer.getId();
    assertEquals(n, 3);
  }

  @Test
  public void getCash() {
    assertEquals(customer.getCash(), new BigDecimal(100));
  }

  @Test
  public void getCardCash() {
    assertEquals(customer.getCardCash(), new BigDecimal(150));
  }

  @Test
  public void getBonuses() {
    assertEquals(customer.getBonuses(), new BigDecimal(60));
  }

  @Test
  public void setBonuses() {
    customer.setBonuses(new BigDecimal(20));
    assertEquals(customer.getBonuses(), new BigDecimal(80));
  }

  @Test
  public void getPaymentMethod() {
    assertEquals(customer.getPaymentMethod(), Method.Card);
  }

  @Test
  public void addProductToBasket() {
    customer.addProductToBasket(product.getProductId(), 4);
    assertEquals(customer.getBasket().getBasketSize(),1);
  }

  @Test
  public void removeProductFromBasket() {
    customer.removeProductFromBasket(product.getProductId());
    assertEquals(customer.getBasket().getBasketSize(),0);
  }

  @Test
  public void getBasket() {
    customer.addProductToBasket(product.getProductId(), 4);
    customer.addProductToBasket(product.getProductId(), 4);
    assertEquals(customer.getBasket().getBasketSize(), 1);
  }

  @Test
  public void clearBasket() {
    customer.clearBasket();
    assertEquals(customer.getBasket().getBasketSize(), 0);
  }

  @Test
  public void issetProductsInBasket() {
    assertFalse(customer.issetProductsInBasket());
  }

  @Test
  public void isAdult() {
    assertFalse(customer.isAdult());
  }

  @Test
  public void isRetired() {
    assertFalse(customer.isRetired());
  }

  @Test
  public void updateCash() {
    customer.updateCash(new BigDecimal(20));
    assertEquals(customer.getCash(), new BigDecimal(20));
  }

  @Test
  public void updateCardCash() {
    customer.updateCardCash(new BigDecimal(20));
    assertEquals(customer.getCardCash(), new BigDecimal(20));
  }

  @Test
  public void updateBonuses() {
    customer.updateBonuses(new BigDecimal(20));
    assertEquals(customer.getBonuses(), new BigDecimal(20));
  }
}