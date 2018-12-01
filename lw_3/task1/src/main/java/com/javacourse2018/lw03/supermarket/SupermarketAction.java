package com.javacourse2018.lw03.supermarket;

import java.util.concurrent.ThreadLocalRandom;

public class SupermarketAction {
    public static final int ACTION_CUSTOMER_CAME_IN = 1;
    public static final int ACTION_CUSTOMER_CAME_OUT = 2;
    public static final int ACTION_CUSTOMER_PUT_IN_BASKET = 3;
    public static final int ACTION_CUSTOMER_JOIN_QUEUE = 4;
    public static final int ACTION_CUSTOMER_LEFT_QUEUE = 5;
    public static final int ACTION_CUSTOMER_SERVE_NEXT = 6;

    private int[] actionPriority = {
            ACTION_CUSTOMER_CAME_IN,
            ACTION_CUSTOMER_CAME_IN,
            ACTION_CUSTOMER_CAME_OUT,
            ACTION_CUSTOMER_PUT_IN_BASKET,
            ACTION_CUSTOMER_PUT_IN_BASKET,
            ACTION_CUSTOMER_PUT_IN_BASKET,
            ACTION_CUSTOMER_PUT_IN_BASKET,
            ACTION_CUSTOMER_JOIN_QUEUE,
            ACTION_CUSTOMER_JOIN_QUEUE,
            ACTION_CUSTOMER_JOIN_QUEUE,
            ACTION_CUSTOMER_JOIN_QUEUE,
            ACTION_CUSTOMER_SERVE_NEXT,
            ACTION_CUSTOMER_SERVE_NEXT,
            ACTION_CUSTOMER_SERVE_NEXT
    };

    public int getNextRandomEvent() {
        return this.actionPriority[ThreadLocalRandom.current().nextInt(
                0,
                this.actionPriority.length - 1)];
    }
}
