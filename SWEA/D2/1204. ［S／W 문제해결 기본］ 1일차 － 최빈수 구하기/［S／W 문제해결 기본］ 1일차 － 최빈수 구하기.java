import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);
            N = Integer.parseInt(br.readLine());

            int[] count = new int[101];
            String[] inputs = br.readLine().split(" ");
            for (String input : inputs) {
                count[Integer.parseInt(input)]++;
            }

            int maxScr = 0, maxCnt = Integer.MIN_VALUE;
            for (int j = 0 ; j <= 100 ; j++) {

                if (maxCnt <= count[j]) {
                    maxScr = j;
                    maxCnt = count[j];
                }

            }

            bw.write(" " + maxScr + "\n");

        }

        bw.close();

    }

}