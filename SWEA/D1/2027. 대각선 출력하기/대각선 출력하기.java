import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        for (int i = 0 ; i < 5 ; i++) {

            for (int j = 0 ; j < 5 ; j++) {

                if (i == j) {
                    bw.write("#");
                } else {
                    bw.write("+");
                }
                
            }

            bw.write("\n");

        }

        bw.close();

    }

}