package report;

import product.Product;
import product.ProductReserve;

import java.util.*;

public class Report implements IReport {

    private ProductReserve productStock = new ProductReserve();
    private Map<Integer, Integer> _soldProducts = new HashMap<>();

    public void addSoldProducts(Map<Integer, Integer> soldProducts) {
        soldProducts.forEach((productId, productCount) -> {
            this._soldProducts.put(productId, productCount);
        });
    }

    public Map<Integer, Integer> getSoldProducts() {
        return this._soldProducts;
    }

    public String getSoldProductReport(ProductReserve stock) {
        String[] outputReportData = {" Sold products:\n"};
        int[] totalCount = {0};
        this.getSoldProducts().forEach((id, quantity) -> {
            Product product = stock.GetProductById(id);
            outputReportData[0] += " " + product.getProductName() + " x " + quantity + "\n";
            totalCount[0] += quantity;
        });
        outputReportData[0] += "-----------------\n";
        outputReportData[0] += "Total sold: " + totalCount[0] + "\n";
        return outputReportData[0];
    }

    public void printReport(ProductReserve stock) {
        System.out.println("Report:");
        System.out.println("---------------------------------------");
        System.out.println(getSoldProductReport(stock));
        System.out.println("---------------------------------------");
    }
}
