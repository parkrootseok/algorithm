import java.io.*;


/**
 * BOJ_IOIOI
 * @author parkrootseok
 *
 * - N+1개의 I와 N개의 O가 교대로 나오는 문자열
 */
public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int N;
    static int M;
    static char[] chars;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());
        chars = br.readLine().trim().toCharArray();

        int count = 0;
        int result = 0;
        for (int pos = 1; pos < M - 1; pos++) {

            if (chars[pos - 1] == 'I' && chars[pos] == 'O' && chars[pos + 1] == 'I') {
                count++;

                if (count == N) {
                    count--;
                    result++;
                }

                pos++;

            } else {
                count = 0;
            }

        }

        sb.append(result);
        bw.write(sb.toString());
        bw.close();

    }

}