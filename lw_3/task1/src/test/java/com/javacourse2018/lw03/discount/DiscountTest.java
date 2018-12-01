package com.javacourse2018.lw03.discount;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiscountTest {

  Discount discount = new Discount(10);
  @Test
  public void getPercent() {
    assertEquals(discount.getPercent(), 10);
  }
}