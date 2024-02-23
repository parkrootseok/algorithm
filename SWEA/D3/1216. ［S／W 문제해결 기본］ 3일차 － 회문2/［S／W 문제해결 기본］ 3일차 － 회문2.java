import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K, ANSWER;
    static String[][] map;

    public static void solution(int row, int col) {

        StringBuilder sbA = new StringBuilder();
        for (int j = col ; j < 100 ; j++) {

            sbA.append(map[row][j]);

            String A = sbA.toString();
            String rA = new StringBuilder(sbA.toString()).reverse().toString();

            if (A.equals(rA)) {
                ANSWER = Math.max(ANSWER, A.length());
            }

        }

        sbA = new StringBuilder();
        for (int j = row ; j < 100 ; j++) {

            sbA.append(map[j][col]);

            String A = sbA.toString();
            String rA = new StringBuilder(sbA.toString()).reverse().toString();


            if (A.equals(rA)) {
                ANSWER = Math.max(ANSWER, A.length());
            }

        }

    }

    public static void main(String args[]) throws Exception {

//        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + i);

            N = Integer.parseInt(br.readLine());

            map = new String[100][100];

            for (int j = 0; j < 100; j++) {
                int k = 0;
                String[] inputs = br.readLine().split("");
                for (String input : inputs) {
                    map[j][k++] = input;
                }
            }

            ANSWER = 0;

            for (int j = 0 ; j < 100 ; j++) {
                for (int k = 0 ; k < 100 ; k++) {
                    solution(j, k);
                }
            }

            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}