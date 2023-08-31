package inflearn.sec08.ex12;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Tomato {

    private int x;
    private int y;

    public Tomato(int x, int y) {
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
 * Section 8 - 12 : 토마토
 */
public class Main {

    static Queue<Tomato> t = new LinkedList<>();;
    static int N, M;
    static int[][] farm, dis;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};


    public void solution() {

        while (!t.isEmpty()) {

            Tomato cur = t.poll();

            for (int i = 0 ; i < 4 ; i++) {

                int nx = cur.getX() + dx[i];
                int ny = cur.getY() + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && farm[nx][ny] == 0) {
                    farm[nx][ny] = 1;
                    t.offer(new Tomato(nx, ny));
                    dis[nx][ny] = dis[cur.getX()][cur.getY()] + 1;
                }

            }

        }


    }


    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();   // 가로
        N = sc.nextInt();   // 세로

        farm = new int[N][M];
        dis = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++){
                farm[i][j] = sc.nextInt();
                if (farm[i][j] == 1) t.offer(new Tomato(i, j));
            }
        }

        m.solution();
        boolean flag = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++){
                if (farm[i][j] == 0) flag = false;
            }
        }

        int ANSWER = Integer.MIN_VALUE;
        if(flag) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++){
                    ANSWER = Math.max(ANSWER, dis[i][j]);
                }
            }
        } else {
            System.out.println(-1);
        }

        System.out.println(ANSWER);

    }

}