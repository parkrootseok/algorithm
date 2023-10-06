import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 스택 수열
 */

public class Main {

    static int n;
    static int[] sequence;

    public String solution() {

        Stack<Integer> numbers = new Stack<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 1, j = 0 ; i <= n ; i++) {

            numbers.push(i);
            sb.append("+\n");

            while (j < n && numbers.contains(sequence[j])) {
                numbers.pop();
                j++;
                sb.append("-\n");
            }

        }

        if (!numbers.isEmpty()) {
            return "NO";
        }

        return sb.toString();

    }


    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        sequence = new int[n];
        for (int i = 0 ; i < n ; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(m.solution());
        bw.close();

    }

}