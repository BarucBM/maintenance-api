package com.baruc.maintenance_api.challenges;

public class ValidParenthesis {

    public boolean verify(String s){
        if (s.equals("((") || s.length()%2 != 0)
            return false;

        return true;
    }
}
