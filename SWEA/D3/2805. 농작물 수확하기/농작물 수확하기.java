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
        int mid = N / 2;
        int sum = 0;

        for (i = 0 ; i < (N + 1) / 2 ; i++) {

            for (j = 0 ; j <= i ; j++) {

                int left = mid + j;
                int right = mid - j;

                if (left == right) {
                    sum += field[i][left];
                } else {
                    sum += (field[i][left] + field[i][right]);
                }

            }

        }

        for (int k = i - 1; k > 0 ; k--) {

            for (j = 0 ; j < k ; j++) {

                int left = mid + j;
                int right = mid - j;

                if (left == right) {
                    sum += field[i][left];
                } else {
                    sum += (field[i][left] + field[i][right]);
                }

            }

            i++;

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

            ANSWER = solution();
            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}