import java.io.*;
import java.util.*;

/**
 * BOJ_좌표압축
 * @author parkrootseok\
 */
public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int N;
    static int[] numbers;
    static int[] sortedNumbers;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());

        pq = new PriorityQueue<>();
        numbers = new int[N];
        sortedNumbers = new int[N];
        String[] inputs = br.readLine().trim().split(" ");
        for (int idx = 0; idx < N; idx++) {
            sortedNumbers[idx] = numbers[idx] = Integer.parseInt(inputs[idx]);

        }

        Arrays.sort(sortedNumbers);

        int order = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : sortedNumbers) {
            if (!map.containsKey(n)) {
                map.put(n, order++);
            }
        }

        for (int idx = 0; idx < N; idx++) {
            sb.append(map.get(numbers[idx])).append(" ");
        }

        bw.write(sb.toString());
        bw.close();

    }

}