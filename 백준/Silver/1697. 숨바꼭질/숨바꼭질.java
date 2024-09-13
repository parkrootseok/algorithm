import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * BOJ_숨박꼭질
 * @author parkrootseok
 */
public class Main {

    public static class Subin {

        int position;
        int second;

        public Subin(int position, int second) {
            this.position = position;
            this.second = second;
        }

    }

    public static int MAX = 100_001;

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static int N;
    public static int K;
    public static int second;
    public static boolean[] isVisited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        K = Integer.parseInt(inputs[1]);

        second = Integer.MAX_VALUE;
        isVisited = new boolean[MAX];
        bfs();

        sb.append(second);
        bw.write(sb.toString());
        bw.close();

    }

    public static void bfs() {

        Queue<Subin> subins = new ArrayDeque<>();
        subins.add(new Subin(N, 0));

        while (!subins.isEmpty()) {

            Subin subin = subins.poll();
            int curPos = subin.position;
            int curSecond = subin.second;

            if (curPos == K) {
                second = Math.min(second, curSecond);
            }

            if (curPos * 2 <= MAX - 1 && !isVisited[curPos * 2]) {
                isVisited[curPos * 2] = true;
                subins.add(new Subin(curPos * 2, curSecond + 1));
            }

            if (curPos + 1 <= MAX - 1 && !isVisited[curPos + 1]) {
                isVisited[curPos + 1] = true;
                subins.add(new Subin(curPos + 1, curSecond + 1));
            }

            if (0 <= curPos - 1 && !isVisited[curPos - 1]) {
                isVisited[curPos - 1] = true;
                subins.add(new Subin(curPos - 1, curSecond + 1));
            }


        }

    }

}