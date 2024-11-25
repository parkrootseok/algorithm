import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * BOJ_가운데를말해요
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int size;
    static PriorityQueue<Integer> minHeap;
    static PriorityQueue<Integer> maxHeap;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        size = Integer.parseInt(br.readLine().trim());
        for (int count = 1; count <= size; count++) {

            int number = Integer.parseInt(br.readLine().trim());
            
            if (minHeap.size() == maxHeap.size()) {
                maxHeap.offer(number);
            } else {
                minHeap.offer(number);
            }

            if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {

                if (minHeap.peek() < maxHeap.peek()) {
                    int tmp = minHeap.poll();
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(tmp);
                }

            }

            sb.append(maxHeap.peek()).append("\n");

        }

        bw.write(sb.toString());
        bw.close();

    }

}