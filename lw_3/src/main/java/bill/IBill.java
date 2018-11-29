package bill;

import customer.Customer;
import product.ProductReserve;

import java.math.BigDecimal;

public interface IBill {
    BigDecimal getBill(Customer customer, ProductReserve productsStock);
}
