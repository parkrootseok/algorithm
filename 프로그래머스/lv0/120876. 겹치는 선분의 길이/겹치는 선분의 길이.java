class Solution {
   public int solution(int[][] lines) {

        int answer = 0;
        int[] cnt = new int[201];
        
        for (int[] i : lines) {
            
            int s = i[0] + 100, e = i[1] + 100;
            
            // 해당하는 구간의 인덱스 원소를 1증가 (겹치는 구간는 2번 증가)
            for (int k = s ; k < e ; k++) {
                cnt[k]++;
            }
            
        }
        
        for (int c : cnt) {
            if (c > 1) {
                answer++;
            }
        }
        
        return answer;
        
    }
}

    