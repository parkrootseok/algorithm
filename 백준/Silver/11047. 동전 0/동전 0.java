import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 동전 0
 */

public class Main {

    public static void main(String[] args) throws IOException {

        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        int[] log = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int money = K;
        for (int i = N - 1; 0 <= i; i--) {  // 가장 금액이 큰 금액의 동전부터 사용하여

            int coin = money / coins[i];    // 해당하는 금액의 동전으로 몇 개를 교환 가능한지 보고
            
            if (coin > 0) { // 만약 0개를 초과하면 (즉, 해당 동전의 금액으로 교환할 수 있다면) 
                log[i] = coin;  // 바꾼 동전의 갯수를 기록하고 
                money = money % coins[i];   // 현재 금액에서 차감
            }
            
        }

        int answer = 0;
        for (int l : log) {
            if (l != 0) {
                answer += l;
            }
        }

        bw.write(answer + "\n");
        bw.close();

    }

}