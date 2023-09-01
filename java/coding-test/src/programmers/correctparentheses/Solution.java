package programmers.correctparentheses;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

    public boolean solution(String str) {

        Stack<Character> s = new Stack<>();

        for(Character c : str.toCharArray()) {

            if (c.equals('(')) {
                s.push('(');
            } else {

                if (s.isEmpty()) {
                    return false;
                }

                s.pop();

            }

        }

        if (!s.isEmpty()) {
            return false;
        }

        return true;

    }

    public static void main(String[] args) {

        Solution s = new Solution();
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        System.out.println(s.solution(str));

    }
}
