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
            String[] N = br.readLine().split("");
            String[] original = new String[N.length];
            Arrays.fill(original, "0");

            int j = 0, k = 0, cnt = 0;
            while (true) {

                if (N[j].equals(original[k])) {
                    j++;
                    k++;
                } else {
                    cnt++;
                    Arrays.fill(original, j, original.length, N[j]);
                }

                if (Arrays.stream(N).collect(Collectors.joining()).equals(Arrays.stream(original).collect(Collectors.joining()))) {
                    break;
                }

            }

            bw.write(" "  + cnt + "\n");

        }

        bw.close();

    }

}