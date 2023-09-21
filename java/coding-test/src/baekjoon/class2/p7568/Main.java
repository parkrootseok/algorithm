package baekjoon.class2.p7568;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 덩치
 */
public class Main {

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] xy = new int[n][2];
        for (int i = 0 ; i < n ; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            xy[i][0] = Integer.parseInt(st.nextToken());
            xy[i][1] = Integer.parseInt(st.nextToken());

        }

        for (int i = 0 ; i < n ; i++) {

            int cnt = 1;

            for (int j = 0 ; j < n ; j++) {

                if (i != j && xy[i][0] < xy[j][0] && xy[i][1] < xy[j][1]) {
                    cnt++;
                }

            }

            bw.write(cnt + " ");

        }

        bw.close();

    }


}