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

        double[] scores = new double[N];
        double max = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0 ; i < N ; i++) {
            scores[i] = Double.parseDouble(st.nextToken());
            max = Math.max(max, scores[i]);
        }

        for (int i = 0 ; i < N ; i++) {
            scores[i] = ( scores[i] / max ) * 100;
        }

        System.out.println(Arrays.stream(scores).average().getAsDouble());

    }

}