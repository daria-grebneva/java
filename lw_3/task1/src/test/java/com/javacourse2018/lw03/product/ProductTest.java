package com.javacourse2018.lw03.product;

import com.javacourse2018.lw03.discount.Discount;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductTest {

  private Product product = new Product(
          4,
          "Milk",
          new Discount(0),
          new BigDecimal(50),
          false,

          IProductsReserve.ProductMeasure.PIECES);

  private Product product1 = new Product(
          5,
          "Beer",
          new Discount(0),
          new BigDecimal(57),
          true,

          IProductsReserve.ProductMeasure.UNKNOWN);


  @Test
  public void getProductId() {
    assertEquals(product.getProductId(), 4);
  }

  @Test
  public void getProductName() {
    assertEquals(product.getProductName(), "Milk");
  }

  @Test
  public void getProductPrice() {
    assertEquals(product.getProductPrice(), new BigDecimal(50));
  }

  @Test
  public void isAdultProduct() {
    assertFalse(product.isAdultProduct());
    assertTrue(product1.isAdultProduct());
  }

  @Test
  public void getProductMeasure() {
    assertEquals(product.getProductMeasure(), IProductsReserve.ProductMeasure.PIECES);
  }
}