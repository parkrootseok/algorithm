import java.io.*;

/**
 * BOJ_부분합
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int size;
    static int target;
    static int[] numbers;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        size = Integer.parseInt(inputs[0]);
        target = Integer.parseInt(inputs[1]);
        numbers = new int[size];

        inputs = br.readLine().trim().split(" ");
        for (int s = 0; s < size; s++) {
            numbers[s] = Integer.parseInt(inputs[s]);
        }

        int minLen = Integer.MAX_VALUE;
        int len = 0;
        int sum = 0;
        for (int start = 0, end = 0; end < size; end++) {

            // 종료 포인터를 이동하면서 누적합
            sum += numbers[end];
            len++;

            // 누적합이 목표 이상이면
            if (target <= sum) {

                // 시작 포인터를 이동
                while (start <= end) {

                    // 시작 포인터 옮긴 값이 여전히 목표치 이상이면 계속 이동
                    if (target <= sum - numbers[start]) {
                        sum -= numbers[start];
                        start++;
                        len--;
                    }

                    // 그렇지 않으면 종료
                    else {
                        break;
                    }
                }

                // 최솟값 초기화
                minLen = Math.min(minLen, len);
                
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            minLen = 0;
        }

        sb.append(minLen).append("\n");
        bw.write(sb.toString());
        bw.close();

    }

}