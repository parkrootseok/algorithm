import java.util.*;

class Solution {
    public int[] solution(int[] arr, boolean[] flag) {

        int pos = 0;
        Stack<Integer> result = new Stack<>();
        for (boolean f : flag) {

            if (f) {
                for (int i = 0 ; i < arr[pos] * 2  ; i++) {
                    result.push(arr[pos]);
                }
            } else {
                for (int i = 0 ; i < arr[pos] ; i++) {
                    result.pop();
                }
            }

            pos++;

        }

        return result.stream().mapToInt(i -> i).toArray();

    }
}