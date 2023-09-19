package baekjoon.class2.p9012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 괄호
 */
public class Main {


    public String isValid(String str) {

        String answer = "NO";
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {

            if (c == '(') {
                stack.add(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return "NO";
                }
                stack.pop();
            }

        }

        if (stack.isEmpty()) {
            return "YES";
        }

        return answer;

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            String str = br.readLine();
            System.out.println(m.isValid(str));

        }

        return;

    }

}