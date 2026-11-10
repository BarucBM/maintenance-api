package com.baruc.maintenance_api.challenges;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class ValidParenthesis {

    public boolean verify(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = Map.of('(', ')', '{', '}', '[', ']');
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
                continue;
            }

            if (map.get(stack.pop()) != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
