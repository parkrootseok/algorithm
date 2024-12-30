class Solution {
    public int solution(int n, int[] stations, int w) {
        
        int answer = 0;
        int index = 0;
        int curPosition = 1;
        
        while (curPosition <= n) {
            
            if (index < stations.length && stations[index] - w <= curPosition) {
                curPosition = stations[index++] + w + 1;
                
            } else {    
                answer++;
                curPosition += (2 * w + 1);
            }
            
        }

        return answer;
        
    }
}