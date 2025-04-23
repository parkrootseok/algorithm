import java.util.*;

/**
 * PG_단체사진찍기
 * @author parkrootseok
 */
class Solution {
    
    static String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
    static boolean[] isUsed;
    static String[] queries;
    static int answer;
    
    public int solution(int n, String[] data) {
        answer = 0;
        queries = data;
        isUsed = new boolean[friends.length];
        bruteforce(0, new StringBuilder());
        return answer;
    }
    
    public void bruteforce(int depth, StringBuilder perm) {
        
        if (depth == friends.length) {
             if (isValid(perm.toString())) {
                 answer++;
             }
            return;
        }
        
        for (int index = 0; index < friends.length; index++) {
            if (!isUsed[index]) {
                isUsed[index] = true;
                perm.append(friends[index]);
                bruteforce(depth + 1, perm);
                perm.deleteCharAt(perm.length() - 1);
                isUsed[index] = false;
            }
        }
        
    }
    
    public boolean isValid(String permutation) {
        
        for (String query : queries) {
            
            int offset = Math.abs(permutation.indexOf(query.charAt(0)) - permutation.indexOf(query.charAt(2))) - 1;
            char command = query.charAt(3);
            int condition = query.charAt(4) - '0';

            if (command == '=') {
                if (offset != condition) {
                    return false;
                }
            } else if (command == '>') {
                if (offset <= condition) {
                    return false;
                }
            } else {
                if (offset >= condition) {
                    return false;
                }
            }
            
        }
        
        return true;
        
    }
    
}