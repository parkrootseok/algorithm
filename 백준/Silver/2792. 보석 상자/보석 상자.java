import java.io.*;

/**
 * BOJ_보석상자
 * @author parkrootseok
 *
 * - 각각의 보석은 M가지 서로 다른 색상 중 하나
 * - 모든 보석을 N명의 학생들에게 나눔
 *  - 받지 못하는 학생이 있어도 가능
 * - 한 아이가 독점하면 질투
 *  - 가장 많은 보석을 가져간 학생이 가지고 있는 보석 개수
 *  - 질투심을 최소가 되게 보석을 나눔
 */
public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int N;
    static int M;
    static int[] numbers;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");

        // 모든 보석을 N명의 학생들에게 나눔
        N = Integer.parseInt(inputs[0]);

        // 보석은 M가지 서로 다른 색상 중 한 색상
        M = Integer.parseInt(inputs[1]);

        int total = 0;
        int max = Integer.MIN_VALUE;
        numbers = new int[M];
        for (int idx = 0; idx < M; idx++) {
            // K번 색상 보석의 개수
            numbers[idx] = Integer.parseInt(br.readLine().trim());

            total += numbers[idx];

            // 한번에 가져갈 수 있는 최대 보석수
            max = Math.max(max, numbers[idx]);
        }

        sb.append(binarySearch(1, max));
        bw.write(sb.toString());
        bw.close();

    }

    public static int binarySearch(int left, int right) {


        while (left < right) {

            // 한 사람이 한번에 가져갈 보석수
            int mid = (left + right) / 2;

            // 현재 가져갈 수 있는 사람 수
            int person = calc(mid);

            // 가져갈 수 있는 사람수가 전체보다 적을 때
            // 한 사람이 더 적게 가져갈 수 있음
            if (person <= N) {
                right = mid;
            }

            // 가져갈 수 있는 사람수가 전체보다 많을 때
            // 한 사람이 더 많이 가져갈 수 있음
            else {
                left = mid + 1;
            }

        }

        return right;

    }

    public static int calc(int mid) {
        int sum = 0;
        for (int idx = 0; idx < M; idx++) {

            // mid개 만큼 보석을 나누어 주었을 때 가능한 사람 수
            sum += numbers[idx] / mid;

            // 만약, 나누어 떨어지지 않는다면 이를 가져가야 할 사람이 한명 더 필요
            if (numbers[idx] % mid != 0) {
                sum++;
            }

        }
        return sum;
    }


}