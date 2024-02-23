import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");
            String str = br.readLine();
            String palindrome = new StringBuilder(str).reverse().toString();;

            int answer = 0;
            if (str.equals(palindrome)) {
                answer = 1;
            }

            bw.write(answer + "\n");

        }

        bw.close();

    }

}