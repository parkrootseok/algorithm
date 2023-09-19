package baekjoon.class2.p1920;

/**
 * 수 찾기
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        TreeSet<Integer> numbers = new TreeSet<>();
        StringTokenizer sb = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            numbers.add(Integer.parseInt(sb.nextToken()));
        }

        int n2 = Integer.parseInt(br.readLine());
        sb = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n2; i++) {

            if (numbers.contains(Integer.parseInt(sb.nextToken()))) {
                bw.write(1 + "\n");
            } else {
                bw.write(0 + "\n");
            }

        }

        bw.close();

    }

}