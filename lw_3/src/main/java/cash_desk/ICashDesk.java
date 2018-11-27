package cash_desk;

import customer.Customer;
import product.ProductReserve;
import report.Report;

import java.util.*;

public interface ICashDesk {
    void addCustomerToQueue(int customerId);
    void removeCustomerFromQueue(int customerId);
    void serveNextCustomer(List<Customer> customers, ProductReserve productStock, Report report);
}
