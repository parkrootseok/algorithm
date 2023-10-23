import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 1 ; i <= N ; i++) {
            answer += i;
        }

        bw.write(answer + "\n");
        bw.close();

    }

}