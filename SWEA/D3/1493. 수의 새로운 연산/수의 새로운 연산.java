import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int P, Q, X, Y, K, ANSWER;

    public static void solution(int target) {

        int numbers = 1;
        for (int j = 1 ; j <= 10000 ; j++) {

            int L = 1, k = j;

            while (L <= j) {

                if (numbers == target) {
                    X += L;
                    Y += k;
                    return;
                }

                L++;
                k--;
                numbers++;

            }

        }

    }

    public static int factorial(int N) {
        if (N <= 0) {
            return 0;
        } else if (N == 1) {
            return 1;
        } else {
            return N + factorial(N - 1);
        }
    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            String[] inputs = br.readLine().split(" ");
            P = Integer.parseInt(inputs[0]);
            Q = Integer.parseInt(inputs[1]);

            X = 0; Y = 0;
            solution(P);
            solution(Q);

            ANSWER = factorial(X) + factorial(X + (Y - 2)) - factorial(X - 1);
            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}