package com.baruc.maintenance_api.challenges;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringCalculator {

    public int add(String string){
        int result = 0;
        if (string.isEmpty())
            return result;
        String[] splitString = string.split("[,\n]");
        for (String s : splitString) {
            if (s.isEmpty())
                continue;
            result += Integer.parseInt(s);
        }
        return result;
    }
}
