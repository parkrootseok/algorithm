import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ_대표선수
 * @author parkrootseok
 */
public class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;;

	static int classNumber;
	static int studentNumber;
	static int[][] abilities;
	static int[] students;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		classNumber = Integer.parseInt(st.nextToken());
		studentNumber = Integer.parseInt(st.nextToken());

		abilities = new int[classNumber][studentNumber];
		for (int row = 0; row < classNumber; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < studentNumber; col++) {
				abilities[row][col] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(abilities[row]);
		}

		sb.append(twoPointer());
		bw.write(sb.toString());
		bw.close();

	}

	public static int twoPointer() {

		students = new int[classNumber];
		Arrays.fill(students, studentNumber - 1);

		int result = Integer.MAX_VALUE;
		while (true) {

			int max = Integer.MIN_VALUE;
			int maxPos = 0;
			int min = Integer.MAX_VALUE;

			for (int c = 0; c < classNumber; c++) {

				int ability = abilities[c][students[c]];

				if (max < ability) {
					max = ability;
					maxPos = c;
				}

				if (min > ability) {
					min = ability;
				}

			}

			result = Math.min(result, max - min);

			if (--students[maxPos] == -1) {
				break;
			}

		}

		return result;

	}


}