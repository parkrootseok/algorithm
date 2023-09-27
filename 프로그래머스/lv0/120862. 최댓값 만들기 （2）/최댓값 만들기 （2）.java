import java.util.*;

class Solution {
      public int solution(int[] numbers) {

        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();

        for (int n : numbers) {

            if (n >= 0) {
                positive.add(n);
            }

            if (n <= 0) {
                negative.add(n);
            }

        }

        Collections.sort(positive);
        Collections.sort(negative);
        
        if (positive.size() < 2 && negative.size() < 2) {
            return positive.get(0) * negative.get(0);
        }  else if (negative.size() < 2) {
            return positive.get(positive.size() - 1) * positive.get(positive.size() - 2);
        } else if (positive.size() < 2) {
            return negative.get(negative.size() - 1) * negative.get(negative.size() - 2);
        } else {
            int pos = positive.get(positive.size() - 1) * positive.get(positive.size() - 2), neg = negative.get(negative.size() - 1) * negative.get(negative.size() - 2);
            return pos > neg ? pos : neg;
        }
        
    }
    
}