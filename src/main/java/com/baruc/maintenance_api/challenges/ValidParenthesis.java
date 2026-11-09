package com.baruc.maintenance_api.challenges;

public class ValidParenthesis {

    public boolean verify(String s){
        if (s.equals("(("))
            return false;

        return true;
    }
}
