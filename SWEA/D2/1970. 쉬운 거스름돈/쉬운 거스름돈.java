import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

class Solution {

    static int N, K;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] money = new int[]{50000, 10000, 5000, 1000, 500, 100, 50, 10};

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            bw.write("#" + i + "\n");

            N = Integer.parseInt(br.readLine());
            
            for (int j = 0 ; j < money.length ; j++) {
                int cnt = 0;
                while (N >= money[j]) {
                    cnt = N / money[j];
                    N %= money[j];
                }

                bw.write(cnt + " ");

            }

            bw.write("\n");

        }

        bw.close();

    }

}