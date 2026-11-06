package com.baruc.maintenance_api.challenges;

public class ValidPalindrome {

    public boolean valid(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char cLeft = s.charAt(left);
            char cRight = s.charAt(right);
            if (!Character.isLetterOrDigit(cLeft)) {
                left++;
                continue;
            }

            if (!Character.isLetterOrDigit(cRight)) {
                right--;
                continue;
            }

            if (Character.toLowerCase(cLeft) != Character.toLowerCase(cRight)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
