import supermarket.Supermarket;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int seconds = 0;

            @Override
            public void run() {
                supermarket.runMarketScenario();
                if (++seconds == (supermarket.getWorkingTimeMinutes() * 120)) {
                    timer.cancel();
                    supermarket.showReport();
                }
            }
        }, 1000, 10);

        supermarket.closeMarket();
    }
}
