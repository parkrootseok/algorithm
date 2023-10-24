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

        int[] price;
        int T = Integer.parseInt(br.readLine());


        for (int i = 1 ; i <= T ; i++) {

            StringBuilder sb = new StringBuilder("#" + i);

            int N = Integer.parseInt(br.readLine());
            price = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine()," ");

            int k = 0;
            while (st.hasMoreTokens()) {
                price[k++] = Integer.parseInt(st.nextToken());
            }

            long max = price[k - 1], profit = 0;
            for (int j = N - 2; j >= 0; j--) {

                if(max > price[j]) {
                    profit += max - price[j];
                } else {
                    max = price[j];
                }

            }

            bw.write(sb.append(" " + profit) + "\n");

        }

        bw.close();

    }

}