import java.util.*;

class Solution {
     public String[] solution(String myStr) {

        String[] result = myStr.split("[a-c]");
        result = Arrays.stream(result)
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        if (result.length == 0) {
            return new String[]{"EMPTY"};
        }

        return result;

    }
}