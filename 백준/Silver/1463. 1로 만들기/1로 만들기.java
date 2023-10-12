import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 1로 만들기
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        int N = Integer.parseInt(br.readLine());
        int[] DP = new int[N + 1];

        DP[0] = DP[1] = 0;
        for (int i = 2 ; i <= N ; i++) {

            DP[i] = DP[i - 1] + 1;

            if (i % 2 == 0) {
                DP[i] = Math.min(DP[i], DP[i / 2] + 1);
            }

            if (i % 3 == 0) {
                DP[i] = Math.min(DP[i], DP[i / 3] + 1);
            }

        }

        bw.write(DP[N] + "\n");
        bw.close();

    }

}