package product;

import java.math.BigDecimal;

public interface IProduct {

    int getProductId();
    String getProductName();
    BigDecimal getProductPrice();
    boolean isAdultProduct();
    public IProductsReserve.ProductMeasure getProductMeasure();
}
