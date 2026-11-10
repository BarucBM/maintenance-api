package com.baruc.maintenance_api.challenges;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;

public class ValidParenthesis {

    public boolean verify(String s) {
        if (s.isBlank())
            return false;
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> map = Map.of('(', ')', '{', '}', '[', ']');
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
                continue;
            }
            var last = stack.peek();
            if (Objects.isNull(last)){
                return false;
            }
            if (map.get(stack.pop()) != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
