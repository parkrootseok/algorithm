package baekjoon.class2.p4153;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String ANSWER = "wrong";

            Long a = Long.parseLong(st.nextToken());
            Long b = Long.parseLong(st.nextToken());
            Long c = Long.parseLong(st.nextToken());

            a = (long)Math.pow(a, 2);
            b = (long)Math.pow(b, 2);
            c = (long)Math.pow(c, 2);

            if (a == 0 & b == 0 & c == 0) {
                return;
            }

            if (a + b == c) {
                ANSWER = "right";
            }

            else if (a + c == b) {
                ANSWER = "right";
            }

            else if (b + c == a) {
                ANSWER = "right";
            }

            System.out.println(ANSWER);

        }


    }


}