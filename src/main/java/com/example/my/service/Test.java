package com.example.my.service;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        int pin = 1;
        Integer[] a = new Integer[]{1,2,3};

        List<Integer> numbers = Arrays.asList(a);
        try {
            pin = numbers.stream().filter(i -> i > 0).min(Integer::compareTo).get();
            while (numbers.contains(pin + 1)) {
                pin += 1;
            }
            System.out.println(pin + 1);
        } catch (RuntimeException ex) {
            System.out.println(pin);
        }
    }
}
