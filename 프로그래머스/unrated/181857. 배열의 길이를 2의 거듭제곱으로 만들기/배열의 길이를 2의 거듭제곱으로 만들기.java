import java.util.*;

class Solution {
     public int[] solution(int[] arr) {

        int answerLength = 1;
        
        while (answerLength < arr.length) {
            answerLength *= 2;
        }
        
        return Arrays.copyOf(arr, answerLength);
        
    }
}