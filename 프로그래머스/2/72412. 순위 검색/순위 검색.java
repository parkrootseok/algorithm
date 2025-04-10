import java.util.*;

/**
 * PG_순위검색
 * @author parkrootseok
 */
class Solution {
    
    static final int LANG = 0;
    static final int JOB = 1;
    static final int EXP = 2;
    static final int FOOD = 3;
    static Map<String, List<Integer>> table;
    
    public int[] solution(String[] info, String[] query) {
        
        int[] answer = new int[query.length];
        
        // table 만들기
        // -> 가능한 키 조합을 모두 미리 생성
        table = new HashMap<>();
        for (String i : info) {
            insert(i.split(" "), 0, "");
        }
        
        // 점수 오름차순 정렬
        // -> 이후에 오름차순 정렬을 수행하기 위함
        for (List<Integer> scores : table.values()) {
            Collections.sort(scores);
        }

				// 쿼리 실행        
        int index = 0;
        for (String q : query) {
        
		        // 쿼리 파싱
            String[] fields = q.split(" and ");
            String key = fields[LANG] + fields[JOB] + fields[EXP] + fields[FOOD].split(" ")[0];
            
            // 키 조합이 없을 경우
            if (!table.containsKey(key)) {
                answer[index++] = 0;
                continue;
            }
            
            // 이분 탐색(하한) 진행
            List<Integer> scores = table.get(key);
            int lowerBoundIndex = binarySearch(scores, Integer.parseInt(fields[FOOD].split(" ")[1]));
            
            // 결과 저장
            answer[index++] = scores.size() - lowerBoundIndex;
        }
        
        return answer;
    }
    
    public void insert(String[] fields, int depth, String key) {
    
        if (depth == 4) {
		        // 조합된 키와 점수 테이블에 추가
            table
                .computeIfAbsent(key, k -> new ArrayList<>())
                .add(Integer.parseInt(fields[depth]));
                
            return;
        }
        
        // -를 삽입한 경우 
        insert(fields, depth + 1, key + "-");
        
        // fileds값을 삽입한 경우
        insert(fields, depth + 1, key + fields[depth]);
    
    }
    
    public int binarySearch(List<Integer> scores, int target) {
        
        int left = 0;
        int right = scores.size();
        
        while (left < right) {
	        
            int mid = (left + right) >> 1;
            
            if (scores.get(mid) < target) {
		            // 목표값 보다 작다면, 더 큰 범위를 탐색
                left = mid + 1;
            } else {
		            // target보다 크거나 같다면, 현재 위치를 기록해야 하므로 유지
                right = mid;
            }
        } 
        
        return right;
        
    }
    
}