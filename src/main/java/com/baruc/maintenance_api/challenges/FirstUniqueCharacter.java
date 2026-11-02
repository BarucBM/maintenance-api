package com.baruc.maintenance_api.challenges;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacter {

    public String find(String string){
        if (string == null) {
            throw new IllegalArgumentException("Input string cannot be null");
        }

        Map<String, Integer> contagem = new HashMap<>();
        for (String c : string.split("")) {
            contagem.put(c, contagem.getOrDefault(c, 0) + 1);
        }

        for (String c :  string.split("")) {
            if (contagem.get(c) == 1) {
                return c;
            }
        }

        return "";
    }
}
