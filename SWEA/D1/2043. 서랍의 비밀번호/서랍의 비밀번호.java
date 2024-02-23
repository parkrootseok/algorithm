import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));

    public static void main(String args[]) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int P = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int answer = Math.abs(P - K) + 1;
        System.out.println(answer);


    }
}