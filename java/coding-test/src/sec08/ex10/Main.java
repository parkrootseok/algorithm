package sec08.ex10;

import java.util.Scanner;

/**
 * Section 8 - 10 : 미로찾기
 */
public class Main {

    static int ANSWER = 0, N;

    static int[][] maze;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public void solution(int i, int j) {

        if (i == 7  && j == 7) {
            ANSWER++;
            return;
        }

        for (int idx = 0 ; idx < 4 ; idx++) {

            int ni = i + dx[idx];
            int nj = j + dy[idx];

            if ((1 <= ni && ni <= 7) && (1 <= nj && nj <= 7) && maze[ni][nj] == 0) {
                maze[ni][nj] = 1;
                solution(ni, nj);
                maze[ni][nj] = 0;
            }

        }

    }


    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        maze = new int[8][8];
        for (int i = 1 ; i <= 7 ; i++) {
            for (int j = 1 ; j <= 7 ; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        maze[1][1] = 1;
        m.solution(1, 1);
        System.out.println(ANSWER);

    }

}