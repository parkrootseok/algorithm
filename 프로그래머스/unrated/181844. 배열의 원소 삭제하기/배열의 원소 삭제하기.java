import java.util.*;

class Solution {
 public int[] solution(int[] arr, int[] delete_list) {

        ArrayList<Integer> delete = new ArrayList<>();
        for (int d : delete_list) {
            delete.add(d);
        }

        return Arrays.stream(arr)
                .filter(n -> !delete.contains(n))
                .toArray();

    }
}