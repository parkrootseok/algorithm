package baekjoon.p10809;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();

        int[] numbers = new int[26];
        Arrays.fill(numbers, -1);

        for (int i = 0 ; i < str.length() ; i++) {

            int idx = str.charAt(i) - 'a';

            if (numbers[idx] == -1) {
                numbers[idx] = i;
            }

        }

        for (int n : numbers) {
            bw.write(n + " ");
        }

        bw.newLine();
        bw.close();

    }

}