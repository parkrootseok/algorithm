import java.util.*;

class Solution {
     public String[] solution(String myStr) {

        String[] result = myStr.split("[a-c]");

        if (result.length == 0) {
            return new String[]{"EMPTY"};
        }
        
        result = Arrays.stream(result)
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        return result;

    }

}