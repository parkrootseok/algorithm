import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * BOJ_징검다리건너기
 * @author parkrootseok
 *
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int stoneCount;
    static int max;
    static int[] stones;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        stoneCount = Integer.parseInt(br.readLine().trim());

        max = Integer.MIN_VALUE;
        stones = new int[stoneCount];
        String[] inputs = br.readLine().trim().split(" ");
        for (int count = 0; count < stoneCount; count++) {
            stones[count] = Integer.parseInt(inputs[count]);
        }

        sb.append(lower(1, getNeedPower(0, stoneCount - 1)));
        bw.write(sb.toString());
        bw.close();

    }

    public static long lower(long left, long right) {

        while (left < right) {

            long mid = (left + right) / 2;

            if (isPossible(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }

        }

        return right;

    }

    public static boolean isPossible(long mid) {

        boolean[] isVisited = new boolean[stoneCount];
        Stack<Integer> queue = new Stack<>();
        queue.push(0);
        isVisited[0] = true;

        while (!queue.isEmpty()) {

            int i = queue.pop();

            if (i == stoneCount - 1) {
                return true;
            }

            for (int j = i + 1; j < stoneCount; j++) {

                if (!isVisited[j] && getNeedPower(i, j) <= mid) {
                    queue.push(j);
                    isVisited[j] = true;
                }

            }

        }

        return false;

    }

    public static long getNeedPower(int i, int j) {
        return (long)(j - i) * (long)(1 + Math.abs(stones[i] - stones[j]));
    }

}