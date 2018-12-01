package com.javacourse2018.lw03.cash_desk;

import com.javacourse2018.lw03.customer.Customer;
import com.javacourse2018.lw03.product.ProductReserve;
import com.javacourse2018.lw03.report.Report;

import java.util.*;

public interface ICashDesk {
    void addCustomerToQueue(int customerId);
    void removeCustomerFromQueue(int customerId);
    void serveNextCustomer(List<Customer> customers, ProductReserve productStock, Report report);
}
