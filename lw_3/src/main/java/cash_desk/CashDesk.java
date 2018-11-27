package cash_desk;

import basket.Basket;
import customer.Customer;
import product.Product;
import product.ProductReserve;
import report.Report;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

public class CashDesk implements ICashDesk{

    private List<Integer> customersQueeIds = new ArrayList<>();
//    private Bill bill;

    public void addCustomerToQueue(int customerId) {
        customersQueeIds.add(customerId);
    }

    public void removeCustomerFromQueue(int customerId) {

        List<Integer> result = new ArrayList<>();

        for(int currentCustomerId : this.customersQueeIds) {
            if(currentCustomerId != customerId) {
                result.add(currentCustomerId);
            }
        }

        this.customersQueeIds = result;
    }

    public void serveNextCustomer(List<Customer> customers, ProductReserve productStock, Report report) {
        if (customersQueeIds.size() > 0) {
            int targetCustomerId = customersQueeIds.get(customersQueeIds.size() - 1);
            List<Product> productList = productStock.GetProductList();
            for(Customer customer : customers) {
                if (customer.getId() == targetCustomerId) {
                    Basket clientBasket = customer.getBasket();

                    if (clientBasket.getBasketSize() > 0) {
                        Logger.getLogger("serve customer id: " + customer.getId() + ", type: " + customer.getType());
                        BigDecimal[] totalPrice = {new BigDecimal(0)};
                        clientBasket.getBasketContent().forEach((productId, productCount) -> {
                            for (Product stockProduct : productList) {
                                if (stockProduct.getProductId() == (productId + 1)) {
                                    if (!customer.isAdult() && stockProduct.isAdultProduct()) {
                                        System.out.println("[!] Reject adult product for children - " + stockProduct.toString());
                                        productStock.returnProduct(stockProduct.getProductId(), productCount);
                                        continue;
                                    }
                                    BigDecimal calculateTotal = stockProduct.getProductPrice().multiply(new BigDecimal(productCount));
                                    totalPrice[0] = calculateTotal.plus();
                                    System.out.println("[+] calculate "  + stockProduct.toString() + " (" + productCount + ") "
                                            + stockProduct.getProductMeasure() + ", price: " + stockProduct.getProductPrice());
                                }
                            }
                        });

                        if (totalPrice[0].compareTo(new BigDecimal(0)) == 0) {
                            break;
                        }

                        System.out.println("-> Backet total price: " + totalPrice[0]);
                        System.out.println("-> Сustomer cash: " + customer.getCash());
                        if (customer.isRetired()) {
                            System.out.println("-> Сustomer bonus: " + customer.getBonuses());
                        }

                        if (customer.getCash().compareTo(totalPrice[0]) >= 0) {
                            // todo: payment method
                            System.out.println("-> Customer use cash: " + customer.getCash());
                            customer.updateCash(customer.getCash().subtract(totalPrice[0]));
                            System.out.println("[+] payment complete");
                            System.out.println("-> Customer remain cash " + customer.getCash());
                            report.addSoldProducts(clientBasket.getBasketContent());
                        }
                        else if (customer.getCash().compareTo(totalPrice[0]) < 0
                                && (customer.isRetired() && (totalPrice[0].compareTo(new BigDecimal(customer.getBonuses())) < 0))) {
                            System.out.println("-> Customer use bonuses: " + customer.getBonuses());
                            BigDecimal bdBonuses = new BigDecimal(customer.getBonuses()).subtract(totalPrice[0]);
                            customer.updateBonuses(bdBonuses.intValue());
                            System.out.println("[+] payment complete");
                            System.out.println("-> Customer remain bonuses " + customer.getBonuses());
                            report.addSoldProducts(clientBasket.getBasketContent());
                        }
                        else {
                            System.out.println("[!] Insufficient funds");
                            System.out.println("-> Products from basket return to stock");
                            clientBasket.getBasketContent().forEach(productStock::returnProduct);
                        }

                        customer.clearBasket();
                    }
                }
            }
            customersQueeIds.remove(customersQueeIds.size() - 1);
        }
    }
}
