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

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int start = 1;
        for (int num : sequence) {

            // 수열에서 필요한 수(num)까지 삽입
            // 단, 필요한 숫자가 start보다 작다면 이미 스택에 존재하는 원소이므로 다시 삽입할 필요가 없음
            for (; start <= num ; start++) {
                stack.push(start);
                sb.append("+\n");
            }

            // 수열에 필요한 숫자가 스택의 가장 첫 원소라면 삭제
            // 하지만, 첫 원소가 아니라면 스택에서 꺼낼수 없으므로 이 수열은 스택으로 구현 불가능
            if (stack.peek() == num) {
                stack.pop();
                sb.append("-\n");
            } else {
                return "NO";
            }

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