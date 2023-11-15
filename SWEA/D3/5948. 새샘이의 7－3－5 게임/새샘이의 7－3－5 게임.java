import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K;
    static List<Integer> combination;
    static String[] numbers;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void solution(int depth, int s, int sum) {

        if (depth == 3) {

            if (!combination.contains(sum)) {
                combination.add(sum);
            }

            return;

        }

        for (int i = s; i < numbers.length; i++) {
            solution(depth + 1, i + 1, sum + Integer.parseInt(numbers[i]));
        }

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");
            numbers = br.readLine().split(" ");

            combination = new ArrayList<>();
            solution(0, 0,0);

            Collections.sort(combination, Collections.reverseOrder());
            bw.write(combination.get(4) + "\n");

        }

        bw.close();

    }

}

