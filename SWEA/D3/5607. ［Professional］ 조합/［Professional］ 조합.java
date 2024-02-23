import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, R;
    static long ANSWER;
    static long factorial[] = new long[1000001];
    static int P = 1234567891;

    public static void factorial() {

        factorial[0] = 1;
        for (int i = 1 ; i <= 1000000; i++) {
            factorial[i] = factorial[i - 1] * i % P;
        }

    }

    public static long getInverse(long n, long exp) {

        if(exp == 1){   // 지수가 1일 경우 n을 반환하고
            return n;
        }

        /** 분할 **/
        long divide = getInverse(n, exp/2) % P; // n^exp/2 형태로 변환

        /** 정복 **/
        if(exp % 2 == 0) {  // exp가 짝수이면 n^exp/2 * n^exp/2 = n^exp
            return divide * divide % P;
        } else { // exp가 홀수이면 (n^exp/2 * n^exp/2) * n = n^exp
            return ((divide * divide) % P * n) % P;
        }

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        factorial();
        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            R = Integer.parseInt(inputs[1]);

            long A = factorial[N];
            long B = (factorial[R] * factorial[N - R]) % P;
            long inverseB = getInverse(B, P - 2);

            ANSWER = A * inverseB % P;
            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}