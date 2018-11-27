package report;

import java.util.Map;

public interface IReport {

    public void addSoldProducts(Map<Integer, Integer> soldProducts);
    public Map<Integer, Integer> getSoldProducts();
    public String getSoldProductReport();
    public void printReport();

}
