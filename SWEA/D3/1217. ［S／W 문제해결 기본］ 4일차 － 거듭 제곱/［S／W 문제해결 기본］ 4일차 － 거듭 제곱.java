import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, EXP;
    static int ANSWER;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static int solution(int N, int EXP) {

        if (EXP == 1) {
            return N;
        }

        int divide = solution(N, EXP / 2);

        if (EXP % 2 == 0) {
            return divide * divide;
        } else {
            return divide * divide * N;
        }

    }

    public static void main(String args[]) throws Exception {

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + br.readLine());

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            EXP = Integer.parseInt(inputs[1]);

            bw.write(" " + solution(N, EXP)+ "\n");

        }

        bw.close();

    }

}