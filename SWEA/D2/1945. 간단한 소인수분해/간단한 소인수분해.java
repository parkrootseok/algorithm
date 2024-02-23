import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;

    public static void main(String args[]) throws Exception {

        int[] prime = {2, 3, 5, 7, 11};

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);
            N = Integer.parseInt(br.readLine());

            for (int p : prime) {

                int cnt = 0;
                while (N % p == 0) {
                    cnt++;
                    N /= p;
                }

                bw.write(" " + cnt);

            }

            bw.write("\n");
            bw.flush();

        }

        bw.close();

    }

}