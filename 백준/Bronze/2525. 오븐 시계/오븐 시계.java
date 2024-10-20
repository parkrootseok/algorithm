import java.io.*;

/**
 * BOJ_오븐시계
 * @author parkrootseok
 */
public class Main {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		String[] inputs = br.readLine().trim().split(" ");
		int h = Integer.parseInt(inputs[0]);
		int m = Integer.parseInt(inputs[1]);
		int time = Integer.parseInt(br.readLine().trim());

		m += time;
		h += (m / 60);

		h %= 24;
		m %= 60;

		sb.append(h).append(" ").append(m);
		bw.write(sb.toString());
		bw.close();

	}

}