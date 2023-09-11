package baekjoon.class2.p2775;

/**
 * 부녀회장이 될테야
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] APT = new int[15][15];

        for (int i = 1 ; i <= 14 ; i++) {
            APT[0][i] = i;
        }

        for (int i = 1 ; i <= 14 ; i++) {

            APT[i][1] = 1;
            for (int j = 2 ; j <= 14 ; j++) {
                APT[i][j] = APT[i][j - 1] + APT[i - 1][j];
            }

        }


        int T = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < T ; i++) {

            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            System.out.println("" + APT[k][n]);

        }

    }

}