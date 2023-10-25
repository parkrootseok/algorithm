import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 1 ; i <= T ; i++) {

            int N = Integer.parseInt(br.readLine());
            bw.write("#" + i + "\n");

            for (int j = 0 ; j < N ; j++) {

                for (int k = 0 ; k <= j ; k++) {

                    if(k == 0 || k == j) {
                        bw.write(1 + " ");
                    } else {
                        bw.write(j + " ");
                    }

                }
                bw.write("\n");
            }

        }

        bw.close();

    }

}