package p8958;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < N ; i++) {

            String str = br.readLine();

            int sum = 0, cnt = 0;
            for (char c : str.toCharArray()) {

                if (c == 'X') {
                    cnt = 0;
                }

                if (c == 'O') {
                    cnt++;
                }

                sum += cnt;

            }

            bw.write(sum + "\n");

        }

        bw.close();

    }

}