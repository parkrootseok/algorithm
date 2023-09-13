package baekjoon.class2.p1436;

/**
 * 영화감독 숌
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int answer = 666;
        int cnt = 1;

        while (cnt != n) {

            answer++;

            if (String.valueOf(answer).contains("666")) {
                cnt++;
            }

        }

        System.out.println(answer);

    }

}