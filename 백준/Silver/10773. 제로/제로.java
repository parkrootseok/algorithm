import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 제로
 */

public class Main {

    static Stack<Integer> stack = new Stack<>();

    public void solution(int num) {

        if (num == 0) {
            stack.pop();
            return;
        }

        stack.push(num);

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < k ; i++) {

            m.solution(Integer.parseInt(br.readLine()));

        }

        int answer = stack.stream().mapToInt(i -> i).sum();
        bw.write(answer + "\n");
        bw.close();
    }

}