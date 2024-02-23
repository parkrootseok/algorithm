class Solution {
  
    public int solution(int n) {

        int i = 1, ans = 0;
        while (i <= n) {

            ans++;

            while (ans % 3 == 0 || ans % 10 == 3 || ans / 10 % 10 == 3) {
                // 3의 배수인지 확인
                while (ans % 3 == 0) {
                    ans++;
                }

                // 1의 자리에 3이 있는지 확인
                while (ans % 10 == 3) {
                    ans++;
                }

                // 10의 자리에 3이 있는지 확인
                while (ans / 10 % 10 == 3) {
                    ans++;
                }
            }


            i++;

        }

        return ans;

    }

}
