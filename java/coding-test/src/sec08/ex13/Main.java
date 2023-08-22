package sec08.ex13;

import java.util.Scanner;

/**
 * Section 8 - 13 : 섬나라 아일랜드
 */
public class Main {

    static int N, ANSWER;
    static int[][] info;
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, 1, 0, -1, -1, 1, -1,1 };

    public void solution(int x, int y) {

        for (int i = 0 ; i < dx.length ; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < N && 0 <= ny && ny < N && info[nx][ny] == 1) {
                info[nx][ny] = 0;
                solution(nx, ny);
            }

        }

    }


    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        info = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                info[i][j] = sc.nextInt();
            }
        }

        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                if (info[i][j] == 1) {
                    info[i][j] = 0;
                    m.solution(i, j);
                    ANSWER++;
                }
            }
        }

        System.out.println(ANSWER);

    }

}