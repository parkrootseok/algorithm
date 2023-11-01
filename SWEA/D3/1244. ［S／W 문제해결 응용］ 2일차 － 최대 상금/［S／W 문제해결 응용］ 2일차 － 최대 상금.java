import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, ANSWER = 0, CHANCE;
    static String[] numbers;

    public static void solution(int start, int chance) {

        if (chance == CHANCE) {

            String number = Arrays.stream(numbers).collect(Collectors.joining());
            ANSWER = Math.max(ANSWER, Integer.parseInt(number));

            return;
        }

        for (int i = start; i < numbers.length; i++) {

            for (int j = i + 1; j < numbers.length; j++) {

                String tmp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = tmp;

                solution(start, chance + 1);

                tmp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = tmp;

            }

        }

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            String[] inputs = br.readLine().split(" ");
            numbers = inputs[0].split("");
            CHANCE = Integer.parseInt(inputs[1]);

            if (numbers.length < CHANCE) {
                CHANCE = numbers.length;
            }

            ANSWER = 0;
            solution(0, 0);

            bw.write(" " + ANSWER + "\n");


        }

        bw.close();

    }

}