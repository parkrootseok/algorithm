import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * BOJ_하노이탑
 * @author parkrootseok
 */
public class Main {

	public static final int LEFT = 1;
	public static final int RIGHT = 3;
	public static final int MID = 2;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int diskNumber;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		diskNumber = Integer.parseInt(br.readLine());
		BigInteger count = new BigInteger("2");

		sb.append(count.pow(diskNumber).subtract(new BigInteger("1"))).append("\n");
		if (diskNumber <= 20) {
			hanoi(diskNumber, LEFT, RIGHT, MID);
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void hanoi(int n, int from, int to, int mid) {

		if (n == 0) {
			return;
		}

		// N-1개의 원판을 시작 지점에서 가운데 기둥으로 이동
		hanoi(n - 1, from, mid, to);

		// 시작 지점에 남은 1개의 원판을 도착 지점으로 이동
		sb.append(from).append(" ").append(to).append("\n");

		// N-1개의 원판을 가운데 기둥에서 도착 지점으로 이동
		hanoi(n- 1, mid, to, from);

	}

}