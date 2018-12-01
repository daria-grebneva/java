package com.javacourse2018.lw03.report;

import com.javacourse2018.lw03.product.ProductReserve;

import java.util.Map;

public interface IReport {
    void addSoldProducts(Map<Integer, Integer> soldProducts);

    Map<Integer, Integer> getSoldProducts();

    String getSoldProductReport(ProductReserve stock);

    void printReport(ProductReserve stock);
}
