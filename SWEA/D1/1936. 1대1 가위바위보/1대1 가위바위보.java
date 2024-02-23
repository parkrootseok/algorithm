import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int SCISSOR = 1, ROCK = 2, PAPER = 3;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        if (A == SCISSOR) {

            if (B == PAPER) {
                bw.write("A");
            } else {
                bw.write("B");
            }

        } else if (A == ROCK) {

            if (B == SCISSOR) {
                bw.write("A");
            } else {
                bw.write("B");
            }
            
        } else {

            if (B == ROCK) {
                bw.write("A");
            } else {
                bw.write("B");
            }

        }

        bw.close();

    }

}