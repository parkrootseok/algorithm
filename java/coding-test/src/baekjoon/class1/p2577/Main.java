package baekjoon.class1.p2577;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        int num = A * B * C;
        int[] numbers = new int[10];

        while (num > 0) {

            numbers[num % 10]++;
            num /= 10;

        }

        for (int x : numbers) {
            bw.write(x + "\n");
        }

        bw.close();

    }

}