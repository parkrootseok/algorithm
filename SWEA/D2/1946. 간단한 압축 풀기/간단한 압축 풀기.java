import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + "\n");
            N = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();
            for (int j = 0 ; j < N ; j++) {

                String[] inputs = br.readLine().split(" ");

                for (int k = 0 ; k < Integer.parseInt(inputs[1]) ; k++) {
                    sb.append(inputs[0]);
                }

            }

            while (sb.length() >= 10) {
                bw.write(sb.substring(0, 10) + "\n");
                sb.delete(0, 10);
            }

            bw.write(sb.substring(0) + "\n");
            bw.flush();

        }

        bw.close();

    }

}