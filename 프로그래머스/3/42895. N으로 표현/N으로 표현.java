import java.util.*;

class Solution {
    
    static Set<Integer>[] dp;
    
    public int solution(int N, int number) {
        
        if (N == number) {
            return 1;
        }
        
        int base = 0;
        dp = new Set[9];
        for (int index = 1; index <= 8; index++) {
            
            dp[index] = new HashSet<>();
            
            base += N;
            dp[index].add(base);
            base *= 10;
            
        }
     
        for (int index = 2; index <= 8; index++) {
            
            for (int useCount = 1; useCount < index; useCount++) {
            
                for (int prev : dp[useCount]) {
                    
                    for (int post : dp[index - useCount]) {
                        
                        dp[index].add(prev + post);
                        dp[index].add(prev - post);
                        dp[index].add(prev * post);
                        
                        if (post != 0) {
                            dp[index].add(prev / post);
                        }
                       
                    }
                    
                }
                
            }
            
            if (dp[index].contains(number)) {
                return index;
            }
            
        }
        
        return -1;
        
    }
    
    
}