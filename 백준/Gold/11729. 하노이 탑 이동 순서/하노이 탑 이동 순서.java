import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * BOJ_11729_하노이탑이동순서
 * @author parkrootseok
 */
public class Main {

	public static final int LEFT = 1;
	public static final int CENTER = 2;
	public static final int RIGHT = 3;


	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static int count;
	public static int N;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());

		count = 0;
		hanoi(N, LEFT, CENTER, RIGHT);

		System.out.println(count);
		System.out.println(sb.toString());

	}

	public static void hanoi(int N, int org, int stopover, int dest) {

		count++;

		// 원판이 1개일 때 바로 이동
		if (N == 1) {
			sb.append(org + " " + dest).append("\n");
			return;
		}

		// N-1개의 원판을 1번 기둥에서 2번 기둥으로 이동
		hanoi(N - 1, org, dest, stopover);

		// N번 원판을 1번 기둥에서 3번 기둥으로 이동
		sb.append(org + " " + dest).append("\n");

		// N-1개의 원판을 2번 기둥에서 3번 기둥으로 이동
		hanoi(N-1, stopover, org, dest);


	}


}