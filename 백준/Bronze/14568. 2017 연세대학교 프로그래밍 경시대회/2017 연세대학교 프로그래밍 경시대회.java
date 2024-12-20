import java.util.*;
import java.io.*;

class Main {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;

	static int candyCount;
	static int count;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		candyCount = Integer.parseInt(br.readLine().trim());
		for (int a = 0; a <= candyCount; a++) {

			for (int b = 0; b <= candyCount; b++) {

				for (int c = 0; c <= candyCount; c++) {

					if (a + b + c == candyCount) {

						if (a  >= b + 2) {

							if (a != 0 && b != 0 && c != 0) {
								if (c % 2 == 0) {
									count++;
								}
							}

						}


					}

				}

			}

		}

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}


}