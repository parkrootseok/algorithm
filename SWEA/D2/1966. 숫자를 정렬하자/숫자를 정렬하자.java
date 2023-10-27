import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

class Solution {

    static int N, K;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            bw.write("#" + i + " ");

            N = Integer.parseInt(br.readLine());

            ArrayList<Integer> numbers = new ArrayList<>();
            String[] inputs = br.readLine().split(" ");
            for (String input : inputs) {
                numbers.add(Integer.valueOf(input));
            }

            Collections.sort(numbers);

            for (int num : numbers) {
                bw.write(num + " ");
            }

            bw.write("\n");

        }

        bw.close();

    }

}