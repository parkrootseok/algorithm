import java.util.*;

class Solution {
    public int[] solution(int[] arr) {

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < arr.length; ) {

           if (result.size() != 0 && result.get(result.size() - 1) == arr[i]) {
                result.remove(result.size() - 1);
                i++;
            } else {
                result.add(arr[i++]);
            }

        }

        int[] answer = result.stream().mapToInt(Integer::valueOf).toArray();
        if (answer.length == 0) {
            return new int[]{-1};
        }

        return answer;

    }
}