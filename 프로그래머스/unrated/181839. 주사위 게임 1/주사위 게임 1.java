class Solution {
  public int solution(int a, int b) {
      
        int sum = a % 2 + b % 2;
        
        if (sum == 1) {
            return 2 * (a + b);
        } else if (sum == 2) {
            return (int) (Math.pow(a, 2) + Math.pow(b, 2));
        } else {
            return Math.abs(a - b);
        }
    
  }
    
}