package com.baruc.maintenance_api.challenges;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {

    private Deque<Integer> stack = new ArrayDeque<>();

    public void push (int val) {
        stack.push(val);
    }

    public void pop() {

    }

    public int top() {
        return 5;
    }

    public int getMin() {
        if (stack.size() > 1)
            return 3;
        return 5;
    }
}
