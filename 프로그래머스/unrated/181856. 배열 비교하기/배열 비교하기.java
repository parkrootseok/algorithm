import java.util.*;

class Solution {
      public int solution(int[] arr1, int[] arr2) {

        int answer = 0;
        if ((answer = Integer.compare(arr1.length, arr2.length)) != 0) {
            return answer;
        }

        answer = Integer.compare(Arrays.stream(arr1).sum(), Arrays.stream(arr2).sum());

        return answer;

    }
}