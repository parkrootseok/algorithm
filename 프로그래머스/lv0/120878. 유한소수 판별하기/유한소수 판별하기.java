class Solution {
    
    
    public int GCD(int a, int b) {

        if (b == 0) {
            return a;
        }

        return GCD(b, a % b);

    }

    public int solution(int a, int b) {
        
        int answer = 1;
        int gcd = GCD(Math.max(a, b), Math.min(a, b));

        b /= gcd;
        
        while (b % 2 == 0) {
            b /= 2;
        }

        while (b % 5 == 0) {
            b /= 5;
        }
        
        if (b != 1) {
            answer = 2;
        }

        return answer;

    }
}