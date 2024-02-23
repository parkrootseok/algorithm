import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] numlist, int n) {

        List<Integer> numbers = Arrays.stream(numlist).boxed().collect(Collectors.toList());
        Collections.sort(numbers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                if (Math.abs(o1 - n) != Math.abs(o2 - n)) { // 거리가 다른 경우 오름차순 정렬
                    return Math.abs(o1 - n) - Math.abs(o2 - n);
                } else {    // n으로부터 거리가 같은 경우 큰 숫자가 먼저
                    return o2 - o1;
                }

            }

        });

        return numbers.stream()
                .mapToInt(i -> i)
                .toArray();

    }
}