package supermarket;

import cash_desk.CashDesk;
import customer.Customer;
import customer.ICustomer;
import payment_method.IPaymentMethod;
import payment_method.PaymentMethod;
import product.Product;
import product.ProductReserve;
import report.Report;
import utils.Randomizer;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

public class Supermarket {

    public void openMarket() {
        this.isOpen = true;
    }
    public void closeMarket() {
        this.isOpen = false;
    }
    public int getWorkingTimeMinutes() {
        return workingTimeMinutes;
    }

    private static final int workingTimeMinutes = 1;

    private final SupermarketAction marketEvent = new SupermarketAction();;
    private boolean isOpen;
    private final List<Customer> customers = new ArrayList<>();
    private final ProductReserve productStock = new ProductReserve();
    private CashDesk cashDesk = new CashDesk();
    private Report report = new Report();

    public void runMarketScenario() {

        if (!this.isOpen) {
            openMarket();
            Logger.getLogger("market is opened!");
            configureMarket();
        }

        switch (marketEvent.getNextRandomEvent()) {
            case SupermarketAction.EVENT_CUSTOMER_CAME_IN:
                addRandomCustomer();
                break;
//            case SupermarketEvent.EVENT_CUSTOMER_CAME_OUT:
//                removeRandomCustomer();
//                break;
            case SupermarketAction.EVENT_CUSTOMER_PUT_IN_BUSKET:
                putInRandomProductForCustomer();
                break;
//            case SupermarketEvent.EVENT_CUSTOMER_LAID_BUSKET:
//                cameOutRandomCustomerProduct();
//                break;
            case SupermarketAction.EVENT_CUSTOMER_JOIN_QUEE:
                randomCustomerJoinQuee();
                break;
            case SupermarketAction.EVENT_CUSTOMER_LEFT_QUEE:
                randomCustomerLeftQuee();
                break;
            case SupermarketAction.EVENT_CUSTOMER_SERVE_NEXT:
                serveNextCustomerFromQuee();
                break;
        }
    }

    private void configureMarket() {
        Logger.getLogger("Add products to Supermarket stock");
        productStock.GenerateRandomProductStore();
    }

    private void addRandomCustomer() {
        int code = Randomizer.getRandomInt(0, ICustomer.CustomerType.values().length);
        ICustomer.CustomerType customerType = ICustomer.getCustomTypeByCode(code);
        IPaymentMethod.Method method = IPaymentMethod.getPaymentMethodByCode(code);
        System.out.println("LALALA" + method);
        int id =  Randomizer.getRandomInt(1, 100000000);
        int cash = Randomizer.getRandomInt(50, 500);
        int cardCash = Randomizer.getRandomInt(50, 500);
        int bonuses = (customerType == ICustomer.CustomerType.Retired) ?
                Randomizer.getRandomInt(0, 50) : 0;

        Customer customer = new Customer(
                id,
                customerType,
                method,
                new BigDecimal(cash),
                new BigDecimal(cardCash),
                bonuses
        );

        customers.add(customer);
        Logger.getLogger("new customer (id: " + customer.getId() + ") arrived!");
    }

    private void removeRandomCustomer() {
        if (customers.size() > 0) {
            int rndCustomerIndex = Randomizer.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            customers.remove(rndCustomerIndex);
            Logger.getLogger("customer (id: " + rndCustomer.getId() + ") came out!");
        }
    }

    private void putInRandomProductForCustomer() {
        if (customers.size() > 0) {
            int rndCustomerIndex = Randomizer.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);

            List<Product> productList = productStock.GetProductList();
            int rndProductIndex = Randomizer.getRandomInt(0, productList.size());
            int rndProductCount = Randomizer.getRandomInt(1, 5);

            if (productStock.deductProduct(rndProductIndex, rndProductCount)) {
                rndCustomer.addProductToBasket(rndProductIndex, rndProductCount);
                Product product = productStock.GetProductById(rndProductIndex);
                Logger.getLogger("customer (id: " + rndCustomer.getId() + ") put in basket: "
                        + product.toString() + " (" + rndProductCount + " " + product.getProductMeasure() + ")");
            }
        }
    }

    private void randomCustomerJoinQuee() {
        if (customers.size() > 0) {
            int rndCustomerIndex = Randomizer.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            if (rndCustomer.issetProductsInBasket()) {
                cashDesk.addCustomerToQueue(rndCustomer.getId());
                Logger.getLogger("customer (id: " + rndCustomer.getId() + ") join to query");
            }
        }
    }

    private void randomCustomerLeftQuee() {
        if (customers.size() > 0) {
            int rndCustomerIndex = Randomizer.getRandomInt(0, customers.size());
            Customer rndCustomer = customers.get(rndCustomerIndex);
            cashDesk.removeCustomerFromQueue(rndCustomer.getId());
            Logger.getLogger("customer (id: " + rndCustomer.getId() + ") left to cash desk quee");
        }
    }

    private void serveNextCustomerFromQuee() {
        if (customers.size() > 0) {
            cashDesk.serveNextCustomer(customers, productStock, report);
        }
    }

    public void showReport() {
        this.report.printReport();
    }
}
