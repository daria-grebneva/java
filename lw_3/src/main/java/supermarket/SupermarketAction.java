package supermarket;

import java.util.concurrent.ThreadLocalRandom;

public class SupermarketAction {
    public static int EVENT_CUSTOMER_CAME_IN = 1;
    public static int EVENT_CUSTOMER_CAME_OUT = 2;
    public static int EVENT_CUSTOMER_PUT_IN_BASKET = 3;
    public static int EVENT_CUSTOMER_JOIN_QUEUE = 4;
    public static int EVENT_CUSTOMER_LEFT_QUEUE = 5;
    public static int EVENT_CUSTOMER_SERVE_NEXT = 6;

    private int[] eventPriorityRange = {
            EVENT_CUSTOMER_CAME_IN,
            EVENT_CUSTOMER_CAME_IN,
            EVENT_CUSTOMER_CAME_OUT,
            EVENT_CUSTOMER_PUT_IN_BASKET,
            EVENT_CUSTOMER_PUT_IN_BASKET,
            EVENT_CUSTOMER_PUT_IN_BASKET,
            EVENT_CUSTOMER_PUT_IN_BASKET,
            EVENT_CUSTOMER_JOIN_QUEUE,
            EVENT_CUSTOMER_JOIN_QUEUE,
            EVENT_CUSTOMER_JOIN_QUEUE,
            EVENT_CUSTOMER_JOIN_QUEUE,
            EVENT_CUSTOMER_SERVE_NEXT,
            EVENT_CUSTOMER_SERVE_NEXT,
            EVENT_CUSTOMER_SERVE_NEXT
    };

    public int getNextRandomEvent() {
        return this.eventPriorityRange[ThreadLocalRandom.current().nextInt(
                0,
                this.eventPriorityRange.length - 1)];
    }
}
