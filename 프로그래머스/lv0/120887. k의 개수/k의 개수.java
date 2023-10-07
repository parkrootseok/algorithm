class Solution {
    public int solution(int i, int j, int k) {

        int answer = 0;
       
        for (int start = i ; start <= j ; start++) {
            
            int tmp = start;
            
            while (tmp > 0) {
                if (k == tmp % 10) {
                    answer++;
                }

                tmp /= 10;
            }
            
        }
        
        return answer;
    
    }
}