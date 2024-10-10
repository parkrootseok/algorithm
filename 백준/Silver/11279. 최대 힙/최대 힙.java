import java.io.*;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * BOJ_2xn타일링2
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	static int N;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		PriorityQueue<Long> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o2, o1));

		N = Integer.parseInt(br.readLine().trim());
		for (int count = 0; count < N; count++) {

			long number = Long.parseLong(br.readLine().trim());

			if (number == 0) {
				if (pq.isEmpty()) {
					sb.append("0").append("\n");
				} else {
					sb.append(pq.poll()).append("\n");
				}
			}

			else {
				pq.offer(number);
			}

		}

		bw.write(sb.toString());
		bw.close();

	}

}