import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K, ANSWER;
    static int[] sequence;

    public static void solution(int depth, int sum) {

        if (depth == N) {

            if(sum == K) {
                ANSWER++;
            }

            return;

        }

        solution(depth + 1, sum + sequence[depth]);
        solution(depth + 1, sum);

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            K = Integer.parseInt(inputs[1]);

            sequence = new int[N];
            inputs = br.readLine().split(" ");
            int j = 0;
            for (String input : inputs) {
                sequence[j++] = Integer.parseInt(input);
            }

            ANSWER = 0;
            solution(0,0);
            bw.write(" "  + ANSWER + "\n");

        }

        bw.close();

    }

}