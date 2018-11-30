package bill;

import product.Product;

import java.math.BigDecimal;

public class Bill {

    public static BigDecimal getProductBill(Product product, int quantity) {
        return product.getProductPrice().multiply(new BigDecimal(quantity));
    }

    public static Boolean canBill(BigDecimal currency, BigDecimal[] totalCost) {
        return ((currency).compareTo(totalCost[0]) >= 0);
    }
}
