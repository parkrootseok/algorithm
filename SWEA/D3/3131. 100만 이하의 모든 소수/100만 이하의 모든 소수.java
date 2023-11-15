import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K;
    static int LIMIT = 1000001;
    static boolean[] isPrime = new boolean[LIMIT + 1];
    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void main(String args[]) throws Exception {

        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i <= LIMIT; i++) {
            if (isPrime[i]) {
                for (int j = i * i ; j <= LIMIT ; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 1; i <= LIMIT; i++) {
            if(isPrime[i]) {
                bw.write(i + " ");
            }
        }
        
        bw.close();

    }

}

