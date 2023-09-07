package baekjoon.class2.p15829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Hashing
 */
public class Main {

    private static long L, r = 31, M = 1234567891;
    private static String seq;

    public void solution() {

        long hashValue = 0;
        long pow = 1;
        for (char c : seq.toCharArray()) {

            // 문자열의 갯수가 커질때 r의 n승 값을 M을 넘지 않도록하자
            hashValue += (c - 'a' + 1) * pow % M;
            pow = pow * r % M;

        }

        System.out.println(hashValue % M);

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        L = Integer.parseInt(br.readLine());
        seq = br.readLine();

        m.solution();

    }


}