package baekjoon.p2920;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] numbers = new int[8];

        int i = 0;
        while (st.hasMoreTokens()) {
            numbers[i++] = Integer.parseInt(st.nextToken());
        }

        boolean asc = true, dsc = true;
        for (i = 0 ; i < 8 ; i++) {

            if (numbers[i] != i + 1) {
                asc = false;
            }

            if (numbers[i] != 8 - i) {
                dsc = false;
            }

        }

        if (asc) {
            bw.write("ascending");
        } else if (dsc) {
            bw.write("descending");
        } else {
            bw.write("mixed");
        }

        bw.close();

    }

}