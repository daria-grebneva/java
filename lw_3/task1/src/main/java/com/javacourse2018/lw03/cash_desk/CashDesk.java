package com.javacourse2018.lw03.cash_desk;

import com.javacourse2018.lw03.basket.Basket;
import com.javacourse2018.lw03.bill.Bill;
import com.javacourse2018.lw03.customer.Customer;
import com.javacourse2018.lw03.payment_method.Method;
import com.javacourse2018.lw03.product.Product;
import com.javacourse2018.lw03.product.ProductReserve;
import com.javacourse2018.lw03.report.Report;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

public class CashDesk implements ICashDesk {

    public void addCustomerToQueue(int id) {
        queue.add(id);
    }

    public void removeCustomerFromQueue(int id) {

        List<Integer> result = new ArrayList<>();

        for (int currency : this.queue) {
            if (currency != id) {
                result.add(currency);
            }
        }

        this.queue = result;
    }

    public void serveNextCustomer(List<Customer> customers, ProductReserve stock, Report report) {
        if (queue.size() > 0) {
            int currency = queue.get(queue.size() - 1);
            for (Customer customer : customers) {
                if (customer.getId() == currency) {
                    Basket basket = customer.getBasket();
                    if (basket.getBasketSize() > 0) {
                        Logger.getLogger("serve customer id: " + customer.getId() + ", type: " + customer.getType());
                        BigDecimal[] totalCost = getTotalCost(basket, customer, stock);

                        if (totalCost[0].compareTo(new BigDecimal(0)) == 0) {
                            break;
                        }

                        System.out.println("-> Backet total price: " + totalCost[0]);
                        paymentProccess(customer, totalCost, basket, report, stock);

                        customer.clearBasket();
                    }
                }
            }
            queue.remove(queue.size() - 1);
        }
    }

    private List<Integer> queue = new ArrayList<>();

    private Boolean isNotForChild(Customer customer, Product stockProduct) {
        return (!customer.isAdult() && stockProduct.isAdultProduct());
    }

    private void paymentProccessByMethod(Customer customer, BigDecimal[] totalCost) {
        switch (customer.getPaymentMethod()) {
            case Card:
                payByCard(customer, totalCost);
                break;
            case Cash:
                payByCash(customer, totalCost);
                break;
            case Bonuses:
                payByBonuses(customer, totalCost);
                break;
        }
    }

    private void payByCash(Customer customer, BigDecimal[] totalCost) {
        System.out.println("-> Customer use cash: " + customer.getCash());
        customer.updateCash(customer.getCash().subtract(totalCost[0]));
        System.out.println("[INFO] payment complete");
        System.out.println("-> Customer remain cash " + customer.getCash());
    }

    private void payByBonuses(Customer customer, BigDecimal[] totalCost) {
        if (Bill.canBill(customer.getBonuses(), totalCost)) {
            System.out.println("-> Customer use bonuses: " + customer.getBonuses());
            customer.updateBonuses(customer.getBonuses().subtract(totalCost[0]));
            System.out.println("[INFO] payment complete");
            System.out.println("-> Customer remain bonuses " + customer.getBonuses());
        } else {
            System.out.println("-> Customer use bonuses and cash: " + customer.getBonuses() + " " + customer.getCash());
            BigDecimal bonuses = customer.getBonuses();
            totalCost[0] = totalCost[0].subtract(bonuses);
            customer.updateCash(customer.getCash().subtract(totalCost[0]));
            customer.updateBonuses(new BigDecimal(0));
            System.out.println("[INFO] payment complete");
            System.out.println("-> Customer remain cash " + customer.getCash());
        }
    }

    private void payByCard(Customer customer, BigDecimal[] totalCost) {
        if (Bill.canBill(customer.getCardCash(), totalCost)) {
            customer.updateCardCash(customer.getCardCash().subtract(totalCost[0]));
        } else {

        }
    }

    private BigDecimal[] getTotalCost(Basket basket, Customer customer, ProductReserve stock) {
        List<Product> productList = stock.GetProductList();
        BigDecimal[] totalCost = {new BigDecimal(0)};
        basket.getBasketContent().forEach((id, quantity) -> {
            for (Product product : productList) {
                if (product.getProductId() == (id + 1)) {
                    if (isNotForChild(customer, product)) {
                        returnAdultProduct(product, stock, quantity);
                        continue;
                    }

                    totalCost[0] = totalCost[0].add(increaseTotalCost(product, quantity));
                }
            }
        });

        return totalCost;
    }

    private void returnAdultProduct(Product product, ProductReserve stock, int quantity) {
        System.out.println("[ERR] Cancellation of purchase - age less than 18 - " + product.getProductName());
        stock.returnProduct(product.getProductId(), quantity);
    }

    private BigDecimal increaseTotalCost(Product product, int quantity) {
        BigDecimal cost = Bill.getProductBill(product, quantity);
        System.out.println("[ADD] " + product.getProductName() + " (" + quantity + ") "
                + product.getProductMeasure() + ", price: " + product.getProductPrice());
        return cost.plus();
    }

    private void paymentProccess(Customer customer, BigDecimal[] totalCost, Basket basket, Report report, ProductReserve stock) {
        if (Bill.canBill(customer.getCash(), totalCost)) {
            System.out.println("-> Customer method: " + customer.getPaymentMethod());
            paymentProccessByMethod(customer, totalCost);
            report.addSoldProducts(basket.getBasketContent());
        } else {
            System.out.println("[ERR] Not enough money");
            System.out.println("-> Products from basket return to stock");
            basket.getBasketContent().forEach(stock::returnProduct);
        }
    }

}
