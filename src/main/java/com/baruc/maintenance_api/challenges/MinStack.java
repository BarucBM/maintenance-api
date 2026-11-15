package com.baruc.maintenance_api.challenges;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

    private Deque<Integer> stack = new ArrayDeque<>();
    private Integer minValue = null;

    public void push (int val) {
        stack.push(val);
        if (minValue == null || val < minValue) {
            minValue = val;
        }
    }

    public void pop() {

    }

    public int top() {
        return 5;
    }

    public int getMin() {
        return minValue;
    }
}
