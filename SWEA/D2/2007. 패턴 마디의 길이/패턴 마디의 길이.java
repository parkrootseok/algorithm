import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        String str;
        for (int i = 1 ; i <= T ; i++) {

            str = br.readLine();

            for (int j = 1 ; j < str.length() ; j++) {

                String A = str.substring(0, j);
                String B = str.substring(j, j + j);

                if (A.equals(B)) {
                    bw.write("#" + i + " " + A.length() + "\n");
                    break;
                }

            }

        }

        bw.close();

    }

}