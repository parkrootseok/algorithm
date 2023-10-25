import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");
            int N = Integer.parseInt(br.readLine());
            
            int sum = 0;
            for (int j = 1; j <= N ; j++) {
                if (j % 2 == 0) {
                    sum -= j;
                } else {
                    sum += j;
                }
            }
            
            bw.write(sum + "\n");

        }

        bw.close();

    }

}