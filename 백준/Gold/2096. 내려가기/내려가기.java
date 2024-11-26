import java.io.*;

/**
 * BOJ_내려가기
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int size;
    static int[] max;
    static int[] min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        size = Integer.parseInt(br.readLine().trim());
        max = new int[3];
        min = new int[3];

        for (int count = 0; count < size; count++) {

            String[] inputs = br.readLine().trim().split(" ");
            int a = Integer.parseInt(inputs[0]);
            int b = Integer.parseInt(inputs[1]);
            int c = Integer.parseInt(inputs[2]);

            if (count == 0) {
                max[0] = min[0] = a;
                max[1] = min[1] = b;
                max[2] = min[2] = c;
                continue;
            }

            int tmp0, tmp1, tmp2;

            tmp0 = Math.max(max[0], max[1]) + a;
            tmp1 = Math.max(Math.max(max[0], max[1]), max[2]) + b;
            tmp2 = Math.max(max[1], max[2]) + c;

            max[0] = tmp0;
            max[1] = tmp1;
            max[2] = tmp2;

            tmp0 = Math.min(min[0], min[1]) + a;
            tmp1 = Math.min(Math.min(min[0], min[1]), min[2]) + b;
            tmp2 = Math.min(min[1], min[2]) + c;

            min[0] = tmp0;
            min[1] = tmp1;
            min[2] = tmp2;

        }

        sb.append(Math.max(Math.max(max[0], max[1]), max[2]))
                .append(" ")
                .append(Math.min(Math.min(min[0], min[1]), min[2]));

        bw.write(sb.toString());
        bw.close();

    }

}