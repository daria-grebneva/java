package supermarket;

import cash_desk.CashDesk;
import customer.Customer;
import customer.ICustomer;
import payment_method.IPaymentMethod;
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

    private static int workingTimeMinutes = 1;

    private SupermarketAction marketEvent = new SupermarketAction();

    private boolean isOpen;
    private List<Customer> customers = new ArrayList<>();
    private ProductReserve stock = new ProductReserve();
    private CashDesk cashDesk = new CashDesk();
    private Report report = new Report();

    public void runMarketScenario() {

        if (!this.isOpen) {
            openMarket();
            Logger.getLogger("Market is opened!");
            configureMarket();
        }

        switch (marketEvent.getNextRandomEvent()) {
            case SupermarketAction.EVENT_CUSTOMER_CAME_IN:
                addRandomCustomer();
                break;
            case SupermarketAction.EVENT_CUSTOMER_CAME_OUT:
                removeRandomCustomer();
                break;
            case SupermarketAction.EVENT_CUSTOMER_PUT_IN_BASKET:
                fillRandomlyProducts();
                break;
            case SupermarketAction.EVENT_CUSTOMER_JOIN_QUEUE:
                joinRandomCustomer();
                break;
            case SupermarketAction.EVENT_CUSTOMER_LEFT_QUEUE:
                leftRandomCustomerFromQueue();
                break;
            case SupermarketAction.EVENT_CUSTOMER_SERVE_NEXT:
                serveNextCustomer();
                break;
        }
    }

    private void configureMarket() {
        Logger.getLogger("Add products to Supermarket stock");
        stock.GenerateRandomProductStore();
    }

    private void addRandomCustomer() {
        int code = Randomizer.getRandomInt(0, ICustomer.CustomerType.values().length);
        ICustomer.CustomerType type = ICustomer.getCustomTypeByCode(code);
        IPaymentMethod.Method method = IPaymentMethod.getPaymentMethodByCode(code);
        int id = Randomizer.getRandomInt(1, 100000000);
        int cash = Randomizer.getRandomInt(50, 500);
        int cardCash = Randomizer.getRandomInt(50, 500);
        int bonuses = (type == ICustomer.CustomerType.Retired) ?
                Randomizer.getRandomInt(0, 50) : 0;

        Customer customer = new Customer(
                id,
                type,
                method,
                new BigDecimal(cash),
                new BigDecimal(cardCash),
                new BigDecimal(bonuses)
        );

        customers.add(customer);
        Logger.getLogger("New customer (id: " + customer.getId() + ") ");
    }

    private void removeRandomCustomer() {
        if (customers.size() > 0) {
            int id = Randomizer.getRandomInt(0, customers.size());
            Customer customer = customers.get(id);
            customers.remove(id);
            Logger.getLogger("Customer (id: " + customer.getId() + ") left us");
        }
    }

    private void leftRandomCustomerFromQueue() {
        if (customers.size() > 0) {
            int id = Randomizer.getRandomInt(0, customers.size());
            Customer customer = customers.get(id);
            cashDesk.removeCustomerFromQueue(customer.getId());
            Logger.getLogger("Customer (id: " + customer.getId() + ") left the queue");
        }
    }

    private void fillRandomlyProducts() {
        if (customers.size() > 0) {
            int id = Randomizer.getRandomInt(0, customers.size());
            Customer customer = customers.get(id);

            List<Product> productList = stock.GetProductList();
            int productId = Randomizer.getRandomInt(0, productList.size());
            int quantity = Randomizer.getRandomInt(1, 5);

            if (stock.removeProduct(productId, quantity)) {
                customer.addProductToBasket(productId, quantity);
                Product product = stock.GetProductById(productId);
                Logger.getLogger("Customer (id: " + customer.getId() + ") put in basket: "
                        + product.toString() + " (" + quantity + " " + product.getProductMeasure() + ")");
            }
        }
    }

    private void joinRandomCustomer() {
        if (customers.size() > 0) {
            int index = Randomizer.getRandomInt(0, customers.size());
            Customer customer = customers.get(index);
            if (customer.issetProductsInBasket()) {
                cashDesk.addCustomerToQueue(customer.getId());
                Logger.getLogger("Customer (id: " + customer.getId() + ") join to query");
            }
        }
    }

    private void serveNextCustomer() {
        if (customers.size() > 0) {
            cashDesk.serveNextCustomer(customers, stock, report);
        }
    }

    public void showReport() {
        this.report.printReport(stock);
    }
}
