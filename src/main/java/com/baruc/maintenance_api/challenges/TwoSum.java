package com.baruc.maintenance_api.challenges;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TwoSum {

    public List<Integer> twoSumSolution(ArrayList<Integer> numbers, int target) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> numbersPosition = new HashMap<>();
        for(int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            if(numbersPosition.containsKey(target - number)) {
                result.add(numbersPosition.get(target - number));
                result.add(i);
                break;
            }
            numbersPosition.put(number, i);
        }

        return result;
    }
}
