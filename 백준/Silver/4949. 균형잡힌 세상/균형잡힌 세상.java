import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public String solution(String str) {

        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {

            if (c == '(' || c == '[') {
                stack.push(c);
            }

            if (c == ')') {
                if (stack.isEmpty()) {
                    return "no";
                } else if (stack.pop() != '(') {
                    return "no";
                }
            }

            else if (c == ']') {
                if (stack.isEmpty()) {
                    return "no";
                } else if (stack.pop() != '[') {
                    return "no";
                }
            }

        }

        if (!stack.isEmpty()) {
            return "no";
        }

        return "yes";

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {

            String str = br.readLine();

            if (str.equals(".")) {
                return;
            }

            System.out.println(m.solution(str));

        }


    }

}