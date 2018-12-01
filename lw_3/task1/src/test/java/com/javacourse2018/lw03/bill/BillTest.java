package com.javacourse2018.lw03.bill;

import com.javacourse2018.lw03.discount.Discount;
import com.javacourse2018.lw03.product.IProductsReserve;
import com.javacourse2018.lw03.product.Product;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BillTest {
  private Product product = new Product(
          4,
          "Milk",
          new Discount(0),
          new BigDecimal(50),
          true,
          IProductsReserve.ProductMeasure.PIECES);
  @Test
  public void getProductBill() {
    Assert.assertEquals(Bill.getProductBill(product, 3), new BigDecimal(150));
  }

  @Test
  public void canBill() {
    Assert.assertEquals(Bill.canBill(new BigDecimal(100), new BigDecimal[]{new BigDecimal(100)}), true);
  }
}