package com.javacourse2018.lw03.product;

import java.math.BigDecimal;

public interface IProduct {

    int getProductId();
    String getProductName();
    BigDecimal getProductPrice();
    boolean isAdultProduct();
    public IProductsReserve.ProductMeasure getProductMeasure();
}
