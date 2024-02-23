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
    static long day = Duration.ofDays(1).toMillis();
    
    public static void main(String args[]) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("MM dd");
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");
            String[] inputs = br.readLine().split(" ");

            long start = format.parse(inputs[0] + " " + inputs[1]).getTime();
            long end = format.parse(inputs[2] + " " + inputs[3]).getTime();
            long answer = ((end - start + day) / day) ;
            bw.write(answer + "\n");

        }

        bw.close();

    }

}