import java.util.*;

class Solution {
 public int[] solution(String myString) {
        String[] strings = myString.split("x", -1);
        return Arrays.stream(strings)
                .mapToInt(String::length)
                .toArray();
    }

}