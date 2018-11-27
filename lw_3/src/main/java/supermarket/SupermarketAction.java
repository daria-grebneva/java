package supermarket;

import java.util.concurrent.ThreadLocalRandom;

public class SupermarketAction {
    public static final int EVENT_CUSTOMER_CAME_IN          = 1;
    //    public static final int EVENT_CUSTOMER_CAME_OUT         = 2;
    public static final int EVENT_CUSTOMER_PUT_IN_BUSKET    = 3;
    public static final int EVENT_CUSTOMER_LAID_BUSKET      = 4;
    public static final int EVENT_CUSTOMER_JOIN_QUEE        = 5;
    public static final int EVENT_CUSTOMER_LEFT_QUEE        = 6;
    public static final int EVENT_CUSTOMER_SERVE_NEXT       = 7;

    private final int[] eventPriorityRange = {
            EVENT_CUSTOMER_CAME_IN,
            EVENT_CUSTOMER_CAME_IN,
//            EVENT_CUSTOMER_CAME_OUT,
            EVENT_CUSTOMER_PUT_IN_BUSKET,
            EVENT_CUSTOMER_PUT_IN_BUSKET,
            EVENT_CUSTOMER_PUT_IN_BUSKET,
            EVENT_CUSTOMER_PUT_IN_BUSKET,
            EVENT_CUSTOMER_LAID_BUSKET,
            EVENT_CUSTOMER_JOIN_QUEE,
            EVENT_CUSTOMER_JOIN_QUEE,
            EVENT_CUSTOMER_JOIN_QUEE,
            EVENT_CUSTOMER_JOIN_QUEE,
            EVENT_CUSTOMER_SERVE_NEXT,
            EVENT_CUSTOMER_SERVE_NEXT,
            EVENT_CUSTOMER_SERVE_NEXT
    };

    public final int getNextRandomEvent() {
        return this.eventPriorityRange[ThreadLocalRandom.current().nextInt(
                0,
                this.eventPriorityRange.length - 1)];
    }
}
