import java.util.*;
import java.io.*;

/**
 * BOJ_체커
 */
class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;

	static int count;
	static int[][] positions;
	static int[] arrX;
	static int[] arrY;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		count = Integer.parseInt(br.readLine().trim());

		positions = new int[count][2];
		arrX = new int[count];
		arrY = new int[count];
		for (int c = 0; c < count; c++) {

			String[] inputs = br.readLine().trim().split(" ");

			int x = Integer.parseInt(inputs[0]);
			int y = Integer.parseInt(inputs[1]);

			positions[c][0] = x;
			positions[c][1] = y;

			arrX[c] = x;
			arrY[c] = y;

		}

		for (int c = 1; c <= count; c++) {

			int min = Integer.MAX_VALUE;

			for (int curX : arrX){

				for (int curY : arrY) {

					PriorityQueue<Integer> pq = new PriorityQueue<>();

					for (int[] pos : positions) {
						pq.add(getDistance(curX, pos[0], curY, pos[1]));
					}

					int index = 1;
					int dist = 0;
					while (!pq.isEmpty() && index <= c) {
						dist += pq.poll();
						index++;
					}

					min = Math.min(min, dist);

				}

			}

			sb.append(min).append(" ");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int getDistance(int x1, int x2, int y1, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}


}