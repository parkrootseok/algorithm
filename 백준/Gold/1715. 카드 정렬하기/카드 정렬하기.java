import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * BOJ_카드정렬하기
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int size;
    static PriorityQueue<Integer> cards;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        size = Integer.parseInt(br.readLine().trim());
        cards = new PriorityQueue<>();
        for (int s = 0; s < size; s++) {
            cards.add(Integer.parseInt(br.readLine().trim()));
        }

        int result = 0;
        while (1 < cards.size()) {

            int a = cards.poll();
            int b = cards.poll();

            result += (a + b);
            cards.offer(a + b);

        }

        sb.append(result);
        bw.write(sb.toString());
        bw.close();

    }

}