import java.util.*;
import java.util.stream.Collectors;

class Solution {
   public int solution(String myString, String pat) {
        int answer = 0;
        String[] split = pat.split("");
        for (int i = 0 ; i < split.length ; i++) {
            if ("A".equals(split[i])) {
                split[i] = "B";
            } else {
                split[i] = "A";
            }
        }

        pat = Arrays.stream(split).collect(Collectors.joining());

        if (myString.contains(pat)) {
            answer = 1;
        }

        return answer;
    }
}