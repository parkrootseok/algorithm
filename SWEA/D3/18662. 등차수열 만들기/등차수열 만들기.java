import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            String[] inputs = br.readLine().split(" ");

            double x = Double.parseDouble(inputs[0]);
            double y = Double.parseDouble(inputs[1]);
            double z = Double.parseDouble(inputs[2]);

            double answer = 0;
            if(y - x != z - y) {
                double mid = (x + z) / 2;
                mid -= y;
                answer = Math.abs(mid);
            }

            bw.write(" " + answer + "\n");

        }

        bw.close();

    }

}