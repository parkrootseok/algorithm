import java.util.*;

class Solution {
    public int[] solution(int[] arr, int k) {

        ArrayList<Integer> result = new ArrayList<>();
        for (int n : arr) {
            if (result.size() != k && !result.contains(n)) {
                result.add(n);
            }
        }
        result.stream().sorted();

        if (result.size() < k) {
            for (int i = result.size() ; i < k ; i++) {
                result.add(-1);
            }
        }

        return result.stream().mapToInt(Integer::valueOf).toArray();

    }
}