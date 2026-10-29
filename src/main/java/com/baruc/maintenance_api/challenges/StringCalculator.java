package com.baruc.maintenance_api.challenges;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringCalculator {

    public int add(String string){
        int result = 0;
        if (string.isEmpty())
            return result;
        String[] splitString = string.split(",");
        for (String s : splitString) {
            result += Integer.parseInt(s);
        }
        return result;
    }
}
