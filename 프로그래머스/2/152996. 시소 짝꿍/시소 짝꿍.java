import java.util.*;

class Solution {
    private Map<Integer, Integer> counter = new HashMap<>();
    
    private long[][] combMemory = new long[100001][3];
    
    public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution(new int[] {100,180,360,100,270}));
	}
    
    public long solution(int[] weights) {
    	long answer = 0;
        for (int weight : weights) {
        	counter.put(weight, counter.getOrDefault(weight, 0) + 1);
        }
        
        int[] noDuplicateWeights = new int[counter.size()];
        
        int idx = 0;
        for (int w : counter.keySet()) {
        	if (counter.get(w) > 1) {
        		answer += comb(counter.get(w), 2);
        	}
        	
        	noDuplicateWeights[idx++] = w;
        }
        
        for (idx = 0; idx < noDuplicateWeights.length; idx++) {
        	for (int oIdx = idx + 1; oIdx < noDuplicateWeights.length; oIdx++) {
        		long w1 = noDuplicateWeights[idx];
        		long w2 = noDuplicateWeights[oIdx];
        		
        		if (w1 * 2 == w2 * 3 || w1 * 2 == w2 * 4 || w1 * 3 == w2 * 4 ||
    				w2 * 2 == w1 * 3 || w2 * 2 == w1 * 4 || w2 * 3 == w1 * 4) {        			
        			answer += (long) counter.get(noDuplicateWeights[idx]) * (long) counter.get(noDuplicateWeights[oIdx]);
        		}
        	}
        }
        
        return answer;
    }
    
    /**
     * n개 중에 r개를 뽑는 경우의 수를 출력하는 메서드
     * @param n
     * @param r
     * @return
     */
    private long comb(int n, int r) {
    	if (combMemory[n][r] != 0) return combMemory[n][r];
    	if (n == r || r == 0) return combMemory[n][r] = 1;
    	else return combMemory[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
    }
}