import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * solved.ac
 */

public class Main {

    static int[] rank;

    public double solution(int n, int excludedNumber) {
        double sum = 0;
        for (int i = excludedNumber ; i < n - excludedNumber ; i++) {
            sum += rank[i];
        }
        return Math.round(sum / (n - excludedNumber * 2));
    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int answer = 0, n = Integer.parseInt(br.readLine());
        rank = new int[n];
        int excludedNumber = (int) Math.round(n * 0.15);
        for (int i = 0 ; i < n ; i++) {
            rank[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(rank);

        answer = (int) m.solution(n, excludedNumber);
        bw.write(answer + "\n");
        bw.close();
    }

}