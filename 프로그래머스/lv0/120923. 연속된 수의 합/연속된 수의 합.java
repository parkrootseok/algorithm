import java.util.*;

class Solution {
    public int[] solution(int num, int total) {

        ArrayList<Integer> log = new ArrayList<>();

        int i, sum = 0;
        for (i = 0 - num ; i < num - num ; i++) {

            log.add(i);
            sum = log.stream()
                    .mapToInt(n -> n)
                    .sum();

        }

        if (sum == total) {
            return log.stream()
                    .mapToInt(n -> n)
                    .toArray();
        }

        for ( ; ; i++) {

            log.remove(log.size() - num);
            log.add(i);
            sum = log.stream()
                    .mapToInt(n -> n)
                    .sum();

            if (sum == total) {
                return log.stream()
                        .mapToInt(n -> n)
                        .toArray();
            }

        }

    }


}