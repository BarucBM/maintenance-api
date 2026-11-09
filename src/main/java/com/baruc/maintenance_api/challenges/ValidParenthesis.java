package com.baruc.maintenance_api.challenges;

public class ValidParenthesis {

    public boolean verify(String s){
        int count1 = 0;
        int count2 = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count1++;
            } else if (c == ')') {
                count1--;
            } else if (c == '[') {
                count2++;
            } else if (c == ']') {
                count2--;
            }
            if (count1 < 0 || count2 < 0) {
                return false;
            }
        }

        return count2 == 0 && count1 == 0;
    }
}
