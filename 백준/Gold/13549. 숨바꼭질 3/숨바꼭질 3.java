import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_숨박꼭질3
 * @author parkrootseok
 */
public class Main {

    public static class Subin implements Comparable<Subin> {

        int position;
        int second;

        public Subin(int position, int second) {
            this.position = position;
            this.second = second;
        }

        @Override
        public int compareTo(Subin s) {
            return Integer.compare(this.second, s.second);
        }

    }

    public static int MAX = 100_000;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;
    public static String[] inputs;

    public static int N;
    public static int K;
    public static int second;
    public static boolean[] isVisited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        K = Integer.parseInt(inputs[1]);

        second = Integer.MAX_VALUE;
        isVisited = new boolean[MAX + 1];
        move();

        sb.append(second);
        bw.write(sb.toString());
        bw.close();

    }

    public static void move() {

        Queue<Subin> queue = new ArrayDeque<>();
        queue.add(new Subin(N, 0));

        while (!queue.isEmpty()) {

            Subin subin = queue.poll();
            int curPosition = subin.position;
            int curSecond = subin.second;

            isVisited[curPosition] = true;
            if (curPosition == K) {
                second = Math.min(second, curSecond);
            }

            if (curPosition * 2 <= MAX && !isVisited[curPosition * 2]) {
                queue.add(new Subin(curPosition * 2, curSecond));
            }

            if (curPosition + 1 <= MAX && !isVisited[curPosition + 1]) {
                queue.add(new Subin(curPosition + 1, curSecond + 1));
            }

            if (0 <= curPosition - 1 && !isVisited[curPosition - 1]) {
                queue.add(new Subin(curPosition - 1, curSecond + 1));
            }

        }

    }

}