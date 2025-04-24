import java.util.*;

/**
 * PG_후보키
 * @author parkrootseok
 */
class Solution {
    
    static String[][] RELATION;
    static int[] isSelected;
    static boolean[] isUsed;
    static Set<String> candidates;
    
    public int solution(String[][] relation) {
        
        RELATION = relation;
        candidates = new HashSet<>();
        for (int limit = 1; limit <= RELATION[0].length; limit++) {
            isUsed = new boolean[RELATION[0].length];
            isSelected = new int[limit];
            dfs(limit, 0);
        }
        
        return candidates.size();
        
    }
    
    public void dfs(int limit, int depth) {
        
        if (depth == limit) {
            
            String key = createKey();
            if (isUnique() && isMinimum(key)) {
                candidates.add(key);
            }
            return;
        }
        
        for (int column = 0; column < RELATION[0].length; column++) {
            if (!isUsed[column]) {
                isUsed[column] = true;
                isSelected[depth] = column;
                dfs(limit, depth + 1);
                isUsed[column] = false;
            }
        }
        
    }
    
    public String createKey() {
        StringBuilder key = new StringBuilder();
        for (int id = 0; id < isSelected.length; id++) {
            key.append(isSelected[id]);        
        }
        return key.toString();
    }
    
    public boolean isUnique() {
        
        Set<String> records = new HashSet<>();
        
        for (int r = 0; r < RELATION.length; r++) {
            
            StringBuilder record = new StringBuilder();
        
            for (int id = 0; id < isSelected.length; id++) {
                record.append(RELATION[r][isSelected[id]]);        
            }
        
            if (records.contains(record.toString())) {
                    return false;
            } else {
                records.add(record.toString());
            }
        
        }
    
        return true;
    
    }    
    
    public boolean isMinimum(String key) {
        
        for (String candidate : candidates) {
            
            int count = 0;
            for (int index = 0; index < key.length(); index++) {
                
                if (candidate.contains(String.valueOf(key.charAt(index)))) {
                    count++;
                }
                
            }
            
            if (candidate.length() == count) {
                return false;
            }
            
        }
        
        return true;
    }  
    
}