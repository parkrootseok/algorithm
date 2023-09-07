package baekjoon.class2.p1259;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Palindrome
 */
public class Main {

    private static String seq;

    public String solution() {

        int lt = 0, rt = seq.length() - 1;

        // two-pointer 알고리즘을 활용하여 동시에 조사
        while (lt < rt) {

            if (seq.charAt(lt++) != seq.charAt(rt--)) {
                return "no";
            }

        }
        return "yes";

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            seq = br.readLine();

            if (seq.equals("0")) {
                return;
            }

            System.out.println(m.solution());

        }

    }


}