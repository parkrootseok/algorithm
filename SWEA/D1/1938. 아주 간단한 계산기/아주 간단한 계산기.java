import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[] operation = new char[]{'+', '-', '*', '/'};
        
        int answer;
        for (char c : operation) {
            
            if (c == '+') {
                answer = n + m;
            } else if (c == '-') {
                answer = n - m;
            } else if (c == '*') {
                answer = n * m;
            } else {
                answer = n / m;
            }
            
            bw.write(answer + "\n");
        }
        
        bw.close();

    }

}