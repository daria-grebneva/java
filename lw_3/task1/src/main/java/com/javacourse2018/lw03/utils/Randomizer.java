package com.javacourse2018.lw03.utils;

import com.javacourse2018.lw03.customer.ICustomer;

import java.util.Random;

public class Randomizer {
    public static int getRandomInt(int lowerBound, int upperBound) {
        return (int) (Math.random() * (upperBound - lowerBound)) + lowerBound;
    }

    public static int getRandomFromArray(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
