package inflearn.sec08.ex15;

import java.util.ArrayList;
import java.util.Scanner;

class Home {

    private int x;
    private int y;

    public Home(int x, int y) {
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

class Shop {

    private int x;
    private int y;

    public Shop(int x, int y) {
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
 * Section 8 - 14 : 피자 배달 거리
 */
public class Main {

    static final int HOME = 1, SHOP = 2;

    static int N, M, ANSWER = Integer.MAX_VALUE;

    static int[][] info;
    static int[] result;
    static ArrayList<Home> homes = new ArrayList<>();
    static ArrayList<Shop> shops = new ArrayList<>();

    public void solution(int L, int s) {

        if (L == M) {   // 조합이 완성되었을 떄(N개의 가게 중 M개의 가게에 대한 조합) 문제에서 제시하는 조건을 수행

            int sum = 0;

            for (Home h : homes) {  // 모든 집을 기준으로

                int dis = Integer.MAX_VALUE;

                for (int x : result) {  // M개의 피자집과의
                    Shop p = shops.get(x);

                    // 가장 가까운 배달 거리를 계산하여
                    dis = Math.min(dis, getDistance(h.getX(), h.getY(), p.getX(), p.getY())); //
                }

                // M개의 가게와 모든 도시에 대한 배달 거리를 구하고
                sum += dis;
            }

            // 모든 도시와의 배달 거리의 최소값을 도출
            ANSWER = Math.min(ANSWER, sum);

        }

        for (int i = s ; i < shops.size() ; i++) {  // N개의 가게 중 M개로 이루어진 조합 생성
            result[L] = i;
            solution(L + 1, i + 1);
        }


    }

    private int getDistance(int x, int y, int nx, int ny) {

        return Math.abs(x - y) + Math.abs(nx - ny);

    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        info = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                int num = sc.nextInt();
                info[i][j] = num;

                if (num == HOME) {
                    homes.add(new Home(i, j));
                }

                if (num == SHOP) {
                    shops.add(new Shop(i, j));
                }
            }
        }

        result = new int[M];

        System.out.println(ANSWER);

    }

}