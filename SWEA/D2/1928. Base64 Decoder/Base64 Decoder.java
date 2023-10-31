import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);
            byte[] decodedBytes = Base64.getDecoder().decode(br.readLine());
            String decodedString = new String(decodedBytes);

            bw.write(" " + decodedString + "\n");
            bw.flush();

        }

        bw.close();

    }

}