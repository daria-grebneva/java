package bill;

import basket.Basket;
import customer.Customer;
import product.Product;
import product.ProductReserve;

import java.math.BigDecimal;
import java.util.*;

public class Bill {

    public static BigDecimal getCustomerBasketBill(Customer customer, ProductReserve productsStock) {
        BigDecimal cost = new BigDecimal(0);
        Basket basket = customer.getBasket();
        Map<Integer, Integer> products = basket.getBasketContent();
        for (Map.Entry<Integer, Integer> pair : products.entrySet()) {
            int id = pair.getKey();
            Product product = productsStock.GetProductById(id);
            int quantity = pair.getValue();
            cost = product.getProductPrice().multiply(new BigDecimal(quantity));
        }

        return cost;
    }

    public static BigDecimal getProductBill(Product product, int quantity) {
        return product.getProductPrice().multiply(new BigDecimal(quantity));
    }

    public static Boolean canBill(BigDecimal currency, BigDecimal[] totalCost) {
        return ((currency).compareTo(totalCost[0]) >= 0);
    }
}
