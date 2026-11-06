package com.baruc.maintenance_api.challenges;

public class ValidPalindrome {

    public boolean valid(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
