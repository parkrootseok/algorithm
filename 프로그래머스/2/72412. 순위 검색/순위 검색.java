import java.util.*;

/**
 * PG_순위검색
 * @author parkrootseok
 */
class Solution {
    
    static Map<String, List<Integer>> table;
    
    public int[] solution(String[] info, String[] query) {
        
        int[] answer = new int[query.length];
        
        // 테이블에 키와 점수 삽입
        table = new HashMap<>();
        for (String i : info) {
            insert(0, i.split(" "), "");
        }
        
        // 점수는 오름차순 정렬
        for (List<Integer> scores : table.values()) {
            Collections.sort(scores);
        }
        
        for (int index = 0; index < query.length; index++) {
            
            // 쿼리 파싱
            String[] conditions = query[index].replace(" and ", " ").split(" ");
            String key = createKey(conditions);
            
            // 키를 포함하고 있다면, 조건에 만족하는 지원자 검색
            if (table.containsKey(key)) {
                List<Integer> scores = table.get(key);
                int rank = lowerBounded(scores, Integer.parseInt(conditions[4]));
                answer[index] = scores.size() - rank;
            }
        
        }
        
        return answer;
        
    }
    
    public int lowerBounded(List<Integer> scores, int target) {
        
        int start = 0;
        int end = scores.size();
        
        while (start < end) {
            
            int mid = (start + end) >> 1;
            
            if (target <= scores.get(mid)) {
                // 더 작은 범위 탐색
                end = mid;
            } else {
                // 더 큰 범위 탐색
                start = mid + 1;
            }
            
        }
        
        return end;
        
    }
    
    public String createKey(String[] conditions) {
        StringBuilder key = new StringBuilder();
        
        key
            .append(conditions[0])
            .append(conditions[1])
            .append(conditions[2])
            .append(conditions[3]);
            
        return key.toString();
    }
    
    public void insert(int depth, String[] columns, String key) {
        
        if (depth == columns.length - 1) {
            table
                .computeIfAbsent(key, k -> new ArrayList<>())
                .add(Integer.parseInt(columns[depth]));
        
            return;
        }
        
        insert(depth + 1, columns, key + columns[depth]);
        insert(depth + 1, columns, key + "-");
        
    }
    
}