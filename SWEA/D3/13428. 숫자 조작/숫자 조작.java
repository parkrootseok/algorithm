import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K;
    static int MAX, MIN;
    static String[] numbers;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void solution(int depth) {

        if (depth == 1) {

            StringBuilder sb = new StringBuilder();

            for (String n : numbers) {
                sb.append(n);
            }

            int number = Integer.parseInt(sb.toString());

            if (String.valueOf(number).length() == sb.length()) {
                MAX = Math.max(MAX, number);
                MIN = Math.min(MIN, number);
            }

            return;

        }

        for (int i = 0 ; i < numbers.length; i++) {

            for (int j = i ; j < numbers.length; j++) {

                String tmp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = tmp;

                solution(depth + 1);

                tmp = numbers[j];
                numbers[j] = numbers[i];
                numbers[i] = tmp;

            }

        }

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");
            numbers = br.readLine().split("");

            MAX = Integer.MIN_VALUE;
            MIN = Integer.MAX_VALUE;

            solution(0);

            bw.write( MIN + " " + MAX +  "\n");

        }

        bw.close();

    }

}

