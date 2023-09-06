package baekjoon.class2.p2292;

import java.io.IOException;
import java.util.Scanner;

/**
 * 벌집
 */
public class Main {

    private static int M, ANSWER = 0;

    public void solution() {

        int cnt = 0, room = 1;
        while (true) {

            room += (cnt * 6);
            cnt++;

            if (room >= M) {
                ANSWER = cnt;
                return ;
            }

        }

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();

        m.solution();
        System.out.println(ANSWER);

    }


}