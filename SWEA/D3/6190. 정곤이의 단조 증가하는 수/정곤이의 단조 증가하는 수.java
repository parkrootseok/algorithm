import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int ANSWER;
    static List<Integer> numbers;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static boolean check(int n) {

        String number = String.valueOf(n);

        if (number.length() == 1) {
            return true;
        }

        for (int k = 0; k < number.length() - 1; k++) {

            if (number.charAt(k) > number.charAt(k + 1)) {
                return false;
            }

        }

        return true;

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            N = Integer.parseInt(br.readLine());
            String[] inputs = br.readLine().split(" ");

            ANSWER = -1;
            numbers = new ArrayList<>();
            for (String input : inputs) {
                numbers.add(Integer.valueOf(input));
            }

            for (int j = 0; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    int number = numbers.get(j) * numbers.get(k);
                    if(check(number)) {
                        ANSWER = Math.max(ANSWER, number);
                    }
                }
            }

            bw.write(ANSWER + "\n");

        }

        bw.close();

    }

}