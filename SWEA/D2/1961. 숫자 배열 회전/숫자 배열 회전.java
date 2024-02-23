import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K;
    static int[][] numbers;
    static ArrayList<StringBuilder> output = new ArrayList<>();

    public static void rotationArray() throws IOException {

        int[][] newArray = new int[N][N];

        for (int x = 0; x < N; x++) {
            for (int y = N - 1; y >= 0; y--) {
                newArray[x][(N - y) - 1] = numbers[y][x];
            }
        }

        numbers = newArray;

    }

    public static void solution() throws IOException {


        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {
                    output.get(i).append(numbers[i][j] + "");
            }

            output.get(i).append(" ");

        }

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + "\n");

            N = Integer.parseInt(br.readLine());
            numbers = new int[N][N];

            for (int j = 0; j < N; j++) {

                output.add(new StringBuilder());
                String[] inputs = br.readLine().split(" ");

                int k = 0;
                for (String input : inputs) {
                    numbers[j][k++] = Integer.parseInt(input);
                }

            }

            for (int j = 0; j < 3; j++) {
                rotationArray();
                solution();
            }

            for (StringBuilder o : output) {
                bw.write(o.toString() + "\n");
            }

            output.clear();
            bw.flush();

        }

        bw.close();

    }

}