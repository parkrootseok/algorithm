import java.util.*;

/**
 * PG_단체사진찍기
 * @author parkrootseok
 */
class Solution {
    
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static char[] permutation = new char[friends.length];
    static boolean[] isUsed = new boolean[friends.length];
    static String[] queries;
    static int answer;
    
    public int solution(int n, String[] data) {
        answer = 0;
        queries = data;
        bruteforce(0);
        return answer;
    }
    
    public void bruteforce(int depth) {
        
        if (depth == friends.length) {
            
            for (String query : queries) {
                
                int offset = Math.abs(getOrder(query.charAt(0)) - getOrder(query.charAt(2))) - 1;
                char command = query.charAt(3);
                int condition = query.charAt(4) - '0';
                
                if (!isValid(command, condition, offset)) {
                    return;
                }
                
            }
            
            answer++;
            return;
            
        }
        
        for (int index = 0; index < friends.length; index++) {
            if (!isUsed[index]) {
                isUsed[index] = true;
                permutation[depth] = friends[index];
                bruteforce(depth + 1);
                isUsed[index] = false;
            }
        }
        
    }
    
    public boolean isValid(char command, int condition, int offset) {
        
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
        
        return true;
        
    }
    
    public int getOrder(char friend) {
        for (int order = 0; order < permutation.length; order++) {
            if (permutation[order] == friend) {
                return order;
            }
        }
        return -1;
    }
    
}