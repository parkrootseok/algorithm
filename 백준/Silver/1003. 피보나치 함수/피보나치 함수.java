import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 피보나치 함수
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    void solution(int N) throws IOException {


        int cnt0 = 1;
        int cnt1 = 0;

        for (int i = 0 ; i < N ; i++) {

            int tmp = cnt0;
            cnt0 = cnt1;
            cnt1 = tmp + cnt0;

        }

        bw.write(cnt0 + " " + cnt1 + "\n");

    }

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < T ; i++) {
            main.solution(Integer.parseInt(br.readLine()));
        }

        bw.close();

    }

}