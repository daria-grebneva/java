package bill;

import basket.Basket;
import customer.Customer;
import product.Product;
import product.ProductReserve;

import java.math.BigDecimal;
import java.util.*;

public class Bill implements IBill {

    public BigDecimal getBill(Customer customer, ProductReserve productsStock) {
        BigDecimal cost = new BigDecimal(0);
        Basket basket = customer.getBasket();
        Map<Integer, Integer> products = basket.getBasketContent();
        for (Map.Entry<Integer, Integer> pair : products.entrySet())
        {
            int id = pair.getKey();
            Product product = productsStock.GetProductById(id);
            int quantity = pair.getValue();
            cost = product.getProductPrice().multiply(new BigDecimal(quantity));
        }

        return cost;
    }
}
