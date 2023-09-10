package baekjoon.class2.p2609;

/**
 * 최대공약수와 최대공배수
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    public static int gcd(int a, int b) {

        while (b != 0) {

            int r = a % b;
            a = b;
            b = r;

        }

        return a;

    }

    public static int lcm(int a, int b) {


        return a * b / gcd(a , b);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());

        System.out.printf("%d\n%d", gcd(A, B), lcm(A, B));

    }

}