import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[][] numbers;

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            M = Integer.parseInt(inputs[1]);

            int[] window = new int[N];
            inputs = br.readLine().split(" ");
            for (int j = 0 ; j < N ; j++) {
                window[j] = Integer.parseInt(inputs[j]);
            }

            int[] numbers = new int[M];
            inputs = br.readLine().split(" ");
            for (int j = 0 ; j < M ; j++) {
                numbers[j] = Integer.parseInt(inputs[j]);
            }

            int max = Integer.MIN_VALUE;
            if (N < M) {
                for (int k = 0 ; k <= numbers.length - window.length; k++) {

                    int sum = 0;

                    for (int j = 0 ; j < window.length ; j++) {
                        sum += numbers[k + j] * window[j];
                    }

                    max = Math.max(max, sum);

                }     
            } else {
                for (int k = 0 ; k <= window.length - numbers.length; k++) {

                    int sum = 0;

                    for (int j = 0 ; j < numbers.length ; j++) {
                        sum += numbers[j] * window[k + j];
                    }

                    max = Math.max(max, sum);

                }
            }
           

            bw.write(" " + max );
            bw.write( "\n");
            bw.flush();

        }

        bw.close();

    }

}