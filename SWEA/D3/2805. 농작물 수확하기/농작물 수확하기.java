import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, ANSWER;
    static int[][] field;

    public static int solution() {

        int i, j;
        int left, right;

        left = right = N / 2;
        int sum = 0;
        for (i = 0 ; i < N ; i++) {

            for (j = left ; j <= right ; j++) {
                sum += field[i][j];
            }

            if (i < N / 2) {
                left--;
                right++;
            } else {
                left++;
                right--;
            }

        }

        return sum;

    }


    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);
            N = Integer.parseInt(br.readLine());
            field = new int[N][N];

            for (int j = 0 ; j < N ; j++) {
                String[] inputs = br.readLine().split("");
                for (int k = 0 ; k < N ; k++) {
                    field[j][k] = Integer.parseInt(inputs[k]);
                }
            }

            bw.write(" " + solution() + "\n");

        }

        bw.close();

    }

}