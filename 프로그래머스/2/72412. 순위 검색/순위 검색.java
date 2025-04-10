import java.util.*;

class Solution {
    
    static final int LANG = 0;
    static final int JOB = 1;
    static final int EXP = 2;
    static final int FOOD = 3;
    static Map<String, List<Integer>> table;
    
    public int[] solution(String[] info, String[] query) {
        
        int[] answer = new int[query.length];
        
        // table 만들기
        table = new HashMap<>();
        for (String i : info) {
            insert(i.split(" "), 0, "");
        }
        
        // 점수 오름차순 정렬
        for (List<Integer> scores : table.values()) {
            Collections.sort(scores);
        }
        
        int index = 0;
        for (String q : query) {
            String[] fields = q.split(" and ");
            String key = fields[LANG] + fields[JOB] + fields[EXP] + fields[FOOD].split(" ")[0];
            
            if (!table.containsKey(key)) {
                answer[index++] = 0;
                continue;
            }
            
            List<Integer> scores = table.get(key);
            int lowerBoundIndex = binarySearch(scores, Integer.parseInt(fields[FOOD].split(" ")[1]));
            answer[index++] = scores.size() - lowerBoundIndex;
        }
        
        return answer;
    }
    
    public void insert(String[] fields, int depth, String key) {
        if (depth == 4) {
            table
                .computeIfAbsent(key, k -> new ArrayList<>())
                .add(Integer.parseInt(fields[depth]));
            return;
        }
        
        insert(fields, depth + 1, key + "-");
        insert(fields, depth + 1, key + fields[depth]);
    
    }
    
    public int binarySearch(List<Integer> scores, int target) {
        
        int left = 0;
        int right = scores.size();
        
        while (left < right) {
            int mid = (left + right) >> 1;
            if (scores.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        } 
        
        return right;
        
    }
    
}