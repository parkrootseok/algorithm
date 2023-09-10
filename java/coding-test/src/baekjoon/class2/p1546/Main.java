package baekjoon.class2.p1546;

/**
 * 평균
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        double score, total = 0;
        double max = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < N ; i++) {
            score = Double.parseDouble(st.nextToken());
            total += score;
            max = Math.max(max, score);
        }

        System.out.println((total / max * 100) / N);

    }

}