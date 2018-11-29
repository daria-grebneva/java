package product;

import java.util.*;
import java.math.BigDecimal;

import discount.Discount;
import utils.Randomizer;

public class ProductReserve implements IProductsReserve {

    private Map<Integer, Integer> productStore = new HashMap<>();
    private List<Product> productList = new ArrayList<>();

    public void GenerateRandomProductStore() {

        FillProductList();
        int productsCount = productList.size();
        int randomCount;

        for (int i = 0; i < productsCount; i++) {
            Product newProduct = this.productList.get(i);
            randomCount = Randomizer.getRandomInt(2, 20);
            productStore.put(newProduct.getProductId(), randomCount);

            System.out.println(" Add product # " + newProduct.getProductId());
            System.out.println(" " + newProduct.getProductName() + " " + newProduct.getProductPrice() + " x " + randomCount);
            System.out.println(" ---------------");
        }
    }

    public List<Product> GetProductList() {
        return productList;
    }

    public void returnProduct(int productId, int count) {
        if (this.productStore.containsKey(productId)) {
            this.productStore.put(productId, this.productStore.get(productId) + count);
        }
    }

    public boolean removeProduct(int productId, int count) {
        if (this.productStore.containsKey(productId)) {
            int storeCount = this.productStore.get(productId);
            if (storeCount > 0 && storeCount >= count) {
                this.productStore.put(productId, this.productStore.get(productId) - count);
                return true;
            }
        }
        return false;
    }

    public Product GetProductById(int productId) {
        return productList.get(productId);
    }

    private void FillProductList() {

        productList.add(new Product(
                1,
                "Milk",
                new Discount(15),
                new BigDecimal(15),
                false,
                ProductMeasure.PIECES)
        );

        productList.add(new Product(
                2,
                "Apple",
                new Discount(0),
                new BigDecimal(25),
                false,
                ProductMeasure.KG)
        );

        productList.add(new Product(
                3,
                "Vodka",
                new Discount(5),
                new BigDecimal(30),
                true,
                ProductMeasure.PIECES)
        );

        productList.add(new Product(
                4,
                "Cigarettes",
                new Discount(0),
                new BigDecimal(50),
                true,
                ProductMeasure.PIECES)
        );

        productList.add(new Product(
                5,
                "Sausage",
                new Discount(15),
                new BigDecimal(40),
                false,
                ProductMeasure.PIECES)
        );

        productList.add(new Product(
                6,
                "Salad",
                new Discount(30),
                new BigDecimal(40),
                false,
                ProductMeasure.PIECES)
        );
    }

}
