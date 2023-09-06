package baekjoon.class2.p1978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    private static int M, ANSWER = 0;
    private static int[] arr;

    public void solution() {

        for (int n : arr) {

            int cnt = 0;
            for (int i = 1 ; i <= n ; i++) {
                if (n % i == 0) {
                    cnt++;
                }
            }

            if (cnt == 2) {
                ANSWER++;
            }

        }
    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        arr = new int[M];
        for (int i = 0 ; i < M ; i++) {
            arr[i] = sc.nextInt();
        }

        m.solution();

        System.out.println(ANSWER);

    }


}