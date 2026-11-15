package com.baruc.maintenance_api.challenges;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

    private Deque<Integer> stack = new ArrayDeque<>();
    private Deque<Integer> minValue = new ArrayDeque<>();

    public void push (int val) {
        stack.push(val);
        Integer lastMin = minValue.peekFirst();
        if (lastMin == null || val <= lastMin) {
            minValue.push(val);
        }
    }

    public void pop() {
        Integer val = stack.pop();
        Integer lastMin = minValue.peekFirst();
        if (val.equals(lastMin)) {
            minValue.pop();
        }
    }

    public Integer top() {
        return stack.peekFirst();
    }

    public Integer getMin() {
        return minValue.peekFirst();
    }
}
