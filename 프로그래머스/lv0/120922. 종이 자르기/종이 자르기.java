class Solution {
    public int solution(int M, int N) {

        if (M == 1 && N == 1) {
            return 0;
        }

        int length = M - 1;
        int width = (length + 1) * (N - 1);

        return length + width;

    }


}