package com.javacourse2018.lw03.cash_desk;

import com.javacourse2018.lw03.customer.Customer;
import com.javacourse2018.lw03.customer.ICustomer;
import com.javacourse2018.lw03.payment_method.Method;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CashDeskTest {

  private CashDesk cashDesk1 = new CashDesk();
  private CashDesk cashDesk2 = new CashDesk();

  private Customer customer = new Customer(
          3,
          ICustomer.CustomerType.Child,
          Method.Card,
          new BigDecimal(100),
          new BigDecimal(150),
          new BigDecimal(60)
  );

  @Test
  public void addCustomerToQueue() {
    List<Integer> queue = new ArrayList<>();
    queue.add(3);
    cashDesk1.addCustomerToQueue(3);
    cashDesk2.addCustomerToQueue(3);
    //assertEquals(cashDesk1, queue);
  }

  @Test
  public void removeCustomerFromQueue() {
    cashDesk1.removeCustomerFromQueue(3);
  }

  @Test
  public void serveNextCustomer() {
  }
}