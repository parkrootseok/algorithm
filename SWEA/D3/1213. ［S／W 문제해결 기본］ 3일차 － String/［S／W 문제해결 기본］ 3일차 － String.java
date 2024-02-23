import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, R;
    static long ANSWER;
    static long factorial[] = new long[1000001];
    static int P = 1234567891;

    public static void main(String args[]) throws Exception {

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + Integer.parseInt(br.readLine()));

            String target = br.readLine();
            String str = br.readLine();

            str = str.replace(target, " ");

            ANSWER = 0;
            for (char s : str.toCharArray()) {
                if (s == ' ') {
                    ANSWER++;
                }
            }

            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}