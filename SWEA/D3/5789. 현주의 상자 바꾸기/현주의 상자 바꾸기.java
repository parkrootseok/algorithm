import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static int N, Q, X, Y;
    static int ANSWER;

    static int[] boxes;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            Q = Integer.parseInt(inputs[1]);

            boxes = new int[N + 1];
            for (int j = 1; j <= Q; j++) {

                inputs = br.readLine().split(" ");
                Arrays.fill(boxes, Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]) + 1, j);

            }

            for (int j = 1; j <= N; j++) {
                bw.write(boxes[j] + " ");
            }

            bw.write("\n");

        }

        bw.close();

    }

}