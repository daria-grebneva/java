package bill;

import discount.Discount;
import org.junit.jupiter.api.Test;
import product.IProductsReserve;
import product.Product;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BillTest {

    private Product product = new Product(
            4,
            "Milk",
            new Discount(0),
            new BigDecimal(50),
            true,
            IProductsReserve.ProductMeasure.PIECES);

    @Test
    void getProductBill() {
        assertEquals(Bill.getProductBill(product, 3), new BigDecimal(150));
    }

    @Test
    void canBill() {
        assertEquals(Bill.canBill(new BigDecimal(100), new BigDecimal[]{new BigDecimal(100)}), true);
    }
}