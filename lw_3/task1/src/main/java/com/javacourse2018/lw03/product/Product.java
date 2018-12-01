package com.javacourse2018.lw03.product;

import java.math.BigDecimal;

import com.javacourse2018.lw03.discount.Discount;

public class Product implements IProduct {

    private int _productId;
    private String _productName;
    private Discount _discount;
    private BigDecimal _price;
    private boolean _isAdultProduct;
    private IProductsReserve.ProductMeasure _measure;

    public Product(int productId, String productName, Discount discount, BigDecimal price, boolean isAdultProduct, IProductsReserve.ProductMeasure measure) {
        this._productId = productId;
        this._productName = productName;
        this._discount = discount;
        this._price = price;
        this._isAdultProduct = isAdultProduct;
        this._measure = measure;
    }

    public int getProductId() {
        return this._productId;
    }

    public String getProductName() {
        return this._productName;
    }

    public BigDecimal getProductPrice() {
        return this._price;
    }

    public boolean isAdultProduct() {
        return this._isAdultProduct;
    }

    public IProductsReserve.ProductMeasure getProductMeasure() { return this._measure; }
}
