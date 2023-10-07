import java.util.*;

class Solution {
    public int solution(String before, String after) {

        char[] be = before.toCharArray();
        char[] af  = after.toCharArray();
        
        Arrays.sort(be);
        Arrays.sort(af);
        
        if (Arrays.equals(be, af)) {
            return 1;
        }
        
        return 0;

    }
}