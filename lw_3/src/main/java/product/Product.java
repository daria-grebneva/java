package product;

import java.math.BigDecimal;

import discount.Discount;

public class Product implements IProduct {

    private int _productId;
    private String _productName;
    private Discount _discount;
    private BigDecimal _price;
    private boolean _isAdultProduct;

    public Product(int productId, String productName, Discount discount, BigDecimal price, boolean isAdultProduct) {
        this._productId = productId;
        this._productName = productName;
        this._discount = discount;
        this._price = price;
        this._isAdultProduct = isAdultProduct;
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

    public boolean IsAdultProduct() {
        return this._isAdultProduct;
    }
}
