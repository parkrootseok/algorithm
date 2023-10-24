import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        String numbers;
        for (int i = 1 ; i <= T ; i++) {

            numbers = String.valueOf(i);

            if (numbers.matches("[0-9]*[369]+[0-9]*")) {
                for (char n : numbers.toCharArray()) {
                    if (n == '3' || n == '6' || n == '9') {
                        bw.write("-");
                    }
                }
            } else {
                bw.write(numbers);
            }

            bw.write(" ");

        }

        bw.close();

    }

}