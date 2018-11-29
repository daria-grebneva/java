package report;

import product.ProductReserve;

import java.util.Map;

public interface IReport {
    void addSoldProducts(Map<Integer, Integer> soldProducts);

    Map<Integer, Integer> getSoldProducts();

    String getSoldProductReport(ProductReserve stock);

    void printReport(ProductReserve stock);
}
