package com.javacourse2018.lw03.payment_method;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class PaymentMethodTest {

  private PaymentMethod method = new PaymentMethod(Method.Card);

  @Test
  public void getPaymentMethod() {
    assertEquals(method.getPaymentMethod(), Method.Card);
    assertNotSame(method.getPaymentMethod(), Method.Bonuses);
    assertNotSame(method.getPaymentMethod(), Method.Cash);
  }
}