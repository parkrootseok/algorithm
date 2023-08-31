package inflearn.sec08.ex11;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
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
 * Section 8 - 11 : 미로 최단거리 통로
 */
public class Main {

    static int[][] maze, dis;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public void solution(int x, int y) {

        Queue<Coordinate> q =  new LinkedList<>();

        maze[1][1] = 1;
        q.offer(new Coordinate(x, y));
        while (!q.isEmpty()) {

            Coordinate coordinate = q.poll();

            for (int idx = 0 ; idx < 4 ; idx++) {

                int nx = coordinate.getX() + dx[idx], ny = coordinate.getY() + dy[idx];

                if (1 <= nx && nx <= 7 && 1 <= ny && ny <= 7 && maze[nx][ny] == 0) {
                    maze[nx][ny] = 1;
                    dis[nx][ny] = dis[coordinate.getX()][coordinate.getY()] + 1;
                    q.offer(new Coordinate(nx, ny));
                    maze[nx][ny] = 0;
                }


            }

        }

    }


    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        dis = new int[8][8];
        maze = new int[8][8];
        for (int i = 1 ; i <= 7 ; i++) {
            for (int j = 1 ; j <= 7 ; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        m.solution(1, 1);

        if (dis[7][7] > 1) {
            System.out.println(dis[7][7]);
        } else {
            System.out.println(-1);
        }

    }

}