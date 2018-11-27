package report;

import product.ProductReserve;

import java.util.*;

public class Report implements IReport {

    private ProductReserve productStock = new ProductReserve();
    private final Map<Integer, Integer> _soldProducts = new HashMap<>();

    public final void addSoldProducts(Map<Integer, Integer> soldProducts) {
        soldProducts.forEach((productId, productCount) -> {
            this._soldProducts.put(productId, productCount);
        });
    }

    public final Map<Integer, Integer> getSoldProducts() {
        return this._soldProducts;
    }

    public final String getSoldProductReport() {
        final String[] outputReportData = {"Stock report (sold products):\n"};
        final int[] totalCount = {0};
        this.getSoldProducts().forEach((productId, productCount) -> {
//            Product product = productStock.GetProductById(productId);
            outputReportData[0] += " - " + productId + " - " + productCount + " " + " cccc " + "\n";
            totalCount[0] += productCount;
        });
        outputReportData[0] += "-----------------\n";
        outputReportData[0] += "Total sold: " + totalCount[0] + "\n";
        return outputReportData[0];
    }

    public final void printReport() {
        System.out.println("Report:");
        System.out.println("---------------------------------------");
        System.out.println(getSoldProductReport());
        System.out.println("---------------------------------------");
    }
}
