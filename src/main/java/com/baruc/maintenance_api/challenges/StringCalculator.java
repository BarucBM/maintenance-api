package com.baruc.maintenance_api.challenges;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StringCalculator {

    public int add(String string){
        int result = 0;
        String separator = "[,\n]";
        if (string.startsWith("//")) {
            String[] splitString = string.split("\n");
            separator = splitString[0].substring(2);
            string = splitString[1];
        }
        String[] splitString = string.split(separator);
        for (String s : splitString) {
            if (s.isEmpty())
                continue;
            result += Integer.parseInt(s);
        }
        return result;
    }
}
