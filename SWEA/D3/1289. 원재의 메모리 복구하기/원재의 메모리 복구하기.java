import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    static int L, ANSWER;
    static String N;

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());


        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);
            String[] input = br.readLine().split("");
            String original = "0";

            int cnt = 0;
            for (int j = 0 ; j < input.length ; j++) {

                if (!input[j].equals(original)) {
                    original = input[j];
                    cnt++;
                }

            }

            bw.write(" "  + cnt + "\n");

        }

        bw.close();

    }

}