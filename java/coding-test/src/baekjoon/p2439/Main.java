package baekjoon.p2439;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int i, j;
        StringBuilder sb = new StringBuilder();
        for (i = 0 ; i < N ; i++) {
            for (j = 0 ; j < N - (i + 1) ; j++) {
               sb.append(" ");
            }

            for (j = 0 ; j < i + 1 ; j++) {
                sb.append("*");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

}