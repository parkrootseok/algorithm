import java.io.*;
import java.util.*;

/**
 * BOJ_공항
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;

	static int G, P;
	static int[] unf;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());

		unf = new int[G + 1];
		for (int g = 1; g <= G; g++) {
			unf[g] = g;
		}

		int answer = 0;
		for (int p = 0; p < P; p++) {
			int gateNumber = Integer.parseInt(br.readLine());
			if (find(gateNumber) == 0) {
				break;
			} else {
				answer++;
				union(find(gateNumber), find(gateNumber) - 1);
			}
		}

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
	}

	static int find(int a) {
		if (unf[a] == a) {
			return a;
		}

		return unf[a] = find(unf[a]) ;
	}

	static void union(int a, int b) {
		a = unf[a];
		b = unf[b];
		unf[a] = b;
	}
	
}
