import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class Solution {

    static int N, K;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        SimpleDateFormat format = new SimpleDateFormat("hh mm");

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");
            String[] time = br.readLine().split(" ");

            Calendar calendarA = Calendar.getInstance();
            calendarA.setTime(format.parse(time[0] + " " + time[1]));

            Calendar calendarB = Calendar.getInstance();
            calendarB.setTime(format.parse(time[2] + " " + time[3]));

            calendarA.add(Calendar.HOUR, calendarB.get(Calendar.HOUR));
            calendarA.add(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));

            int h;
            if (calendarA.get(Calendar.HOUR_OF_DAY) > 12) {
                h = calendarA.get(Calendar.HOUR_OF_DAY) - 12;
            } else {
                h = calendarA.get(Calendar.HOUR_OF_DAY);
            }

            bw.write(h + " " + calendarA.get(Calendar.MINUTE));
            bw.write("\n");

        }

        bw.close();

    }

}