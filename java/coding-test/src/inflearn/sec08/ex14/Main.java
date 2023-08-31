package inflearn.sec08.ex14;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Island {

    private int x;
    private int y;

    public Island(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

/**
 * Section 8 - 14 : 섬나라 아일랜드
 */
public class Main {

    static Queue<Island> islands = new LinkedList<>();
    static int N, ANSWER;
    static int[][] info;
    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, 1, 0, -1, -1, 1, -1,1 } ;



    public void solution(int x, int y) {

        info[x][y] = 0;
        islands.offer(new Island(x , y));
        while (!islands.isEmpty()) {

            Island cur = islands.poll();

            for (int i = 0 ; i < dx.length ; i++) {

                int nx = cur.getX() + dx[i];
                int ny = cur.getY() + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && info[nx][ny] == 1) {
                    info[nx][ny] = 0;
                    islands.offer(new Island(nx, ny));
                }
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
                    m.solution(i, j);
                    ANSWER++;
                }
            }
        }

        m.solution(0, 0);
        System.out.println(ANSWER);

    }

}