import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 소수구하기
 */

public class Main {

    public boolean solution(int num) {

        for (int i = 2 ; i < num ; i++) {

            if (num % i == 0) {
                return false;
            }

        }

        return true;

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int MN = 1000001;
        boolean[] isPrime = new boolean[MN];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
        for (int i = 2 ; i * i < MN ; i++) {
            if (isPrime[i]) {
                for (int j = i * i ; j < MN ; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int M = Integer.parseInt(st.nextToken()), N = Integer.parseInt(st.nextToken());


        for (int i = M ; i <= N ; i++) {
            if (isPrime[i]) {
                bw.write(i + "\n");
            }
        }

        bw.close();

    }

}