import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_숫자카드2
 * @author parkrootseok
 */
public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;
    public static String[] inputs;

    public static int N;
    public static int C;
    public static int[] positions;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        inputs = br.readLine().trim().split(" ");
        N = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);

        positions = new int[N];
        for (int idx = 0; idx < N; idx++) {
            positions[idx] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(positions);

        sb.append(upper(0, positions[N - 1] - positions[0] + 1));
        bw.write(sb.toString());
        bw.close();

    }

    public static int upper(int low, int high) {

        while (low < high) {

            int mid = low + ((high - low) >> 1);
            int count = getWIFICount(mid);

            // 현재 설치 가능한 공유기 개수가 목표값보다 작다면 최소 거리 감소
            if (count < C) {
                high = mid;
            }

            // 더 많다면 최소 거리 증가
            else {
                low = mid + 1;
            }

        }

        return high - 1;

    }

    public static int getWIFICount(int distance) {

        int count = 1;
        int lastPos = positions[0];
        for (int idx = 1; idx < N; idx++) {

            int curPos = positions[idx];


            if (distance <= curPos - lastPos) {
                count++;
                lastPos = curPos;
            }

        }

        return count;

    }

}