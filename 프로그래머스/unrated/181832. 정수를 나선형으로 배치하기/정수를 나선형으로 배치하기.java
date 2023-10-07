class Solution {
public int[][] solution(int n) {
        int[][] answer = new int[n][n];

        int i, j, k;
        int num = 1, dir = 1, rep = n, x = 0, y = -1;
        for (i = 0 ; i < n ; i++) {

            for (j = 0 ; j < rep ; j++) {
                y += dir;
                answer[x][y] = num++;
            }

            rep--;

            for (k = 0 ; k < rep ; k++) {
                x += dir;
                answer[x][y] = num++;
            }

            dir *= -1;

        }

        return answer;

    }
}