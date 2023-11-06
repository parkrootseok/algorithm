import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K, ANSWER;
    static Queue<Integer> numbers;
    static int CYCLE = 6;

    public static void solution() {

        int order = 1;

        while (!numbers.contains(0)) {

            int cur = numbers.poll() - order++;

            if (cur <= 0) {
                numbers.offer(0);
            } else {
                numbers.offer(cur);
            }

            if (order == CYCLE) {
                order = 1;
            }

        }

    }

    public static void main(String args[]) throws Exception {

//        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + i);

            N = Integer.parseInt(br.readLine());

            numbers = new LinkedList<>();
            String[] inputs = br.readLine().split(" ");
            for (String input : inputs) {
                numbers.add(Integer.parseInt(input));
            }

            solution();

            for (int number : numbers) {
                bw.write(" " + number);
            }

            bw.write("\n");

        }

        bw.close();

    }

}