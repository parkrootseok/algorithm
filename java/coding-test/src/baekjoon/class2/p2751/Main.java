package baekjoon.class2.p2751;

/**
 * 수 정렬하기 2
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[2000001];

        // idx로 올 수 있는 수의 범위는 -1,000,000 ~ 1,000,000
        for (int i = 0 ; i < N ; i++) {
            numbers[Integer.parseInt(br.readLine()) + 1000000] = 1;
        }

        for (int i = 0 ; i < numbers.length ; i++) {

            if (numbers[i] == 1) {
                bw.write((i - 1000000) + "\n");
            }

        }

        bw.close();

    }

}