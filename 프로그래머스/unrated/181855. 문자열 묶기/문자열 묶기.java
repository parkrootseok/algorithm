import java.util.*;

class Solution {
    public int solution(String[] strArr) {
        
        int[] cnt = new int[31];
        
        for(String str : strArr) {
            
            cnt[str.length()]++;
            
        }
        
        return Arrays.stream(cnt).max().getAsInt();
    
    }

}