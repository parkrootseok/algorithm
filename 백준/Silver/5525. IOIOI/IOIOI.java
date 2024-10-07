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
    static String str;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());
        str = br.readLine().trim();

        StringBuilder target = new StringBuilder();
        for (int pos = 0; pos < N + (N + 1); pos++) {
            if (pos % 2 == 0) {
                target.append("I");
            } else {
                target.append("O");
            }
        }

        int count = 0;
        int left = 0;
        int right = target.length();
        while (right <= M) {

            String cur = str.substring(left, right);

            if (cur.equals(target.toString())) {
                count++;
            }

            left++;
            right++;

        }

        sb.append(count);
        bw.write(sb.toString());
        bw.close();

    }

}