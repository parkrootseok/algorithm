package inflearn.sec05.solution;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Sec05Solution {

    public Sec05Solution() {
    }

    /**
     * section 5 - 1 : 올바른 괄호
     */
    public String CheckParentheses(String str) {

        String ans = "YES";
        Stack<Character> s = new Stack<>();


        for(char c : str.toCharArray()) {

            if (c == '(') {
                s.push(c);
            } else if (c == ')') {
                if (s.isEmpty()) {
                    return "NO";
                }
                s.pop();
            }
        }

        if (!s.isEmpty()) {
            return "NO";
        }

        return ans;

    }

    /**
     * section 5 - 2 : 괄호 문자 제거
     */
    public String removeCharacterInParentheses(String str) {

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            if (c != ')') {
                stack.push(c);
            } else {
                while (stack.pop() != '(');
            }
        }

       for (char c : stack) {
           sb.append(c);
       }

        return sb.toString();

    }

    /**
     * section 5 - 3 : 크레인 인형뽑기
     */
    public int clawMachine(int[][] board, int M, int[] moves) {

        int ans = 0;
        Stack<Integer> dolls = new Stack<>();

        int doll;
        for (int pos : moves) {

            for (int i = 0 ; i < board.length ; i++) {

                doll = board[i][pos - 1];

                if (doll != 0) {

                    board[i][pos - 1] = 0;

                    if (!dolls.isEmpty() && dolls.peek() == doll) {
                        ans += 2;
                        dolls.pop();
                    } else {
                        dolls.push(doll);
                    }

                    break;

                }

            }


        }

        return ans;

    }

    /**
     * section 5 - 4 : 후위식 연산
     */
    public int calculatePostfix(String s) {

        Stack<Integer> result = new Stack<>();

        int l, r, ans;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                result.push(Character.getNumericValue(c));
            } else {
                r = result.pop();
                l = result.pop();
                switch (c) {
                    case '+':
                        result.push(l + r);
                        break;
                    case '-':
                        result.push(l - r);
                        break;
                    case '*':
                        result.push(l * r);
                        break;
                    case '/':
                        result.push(l / r);
                        break;
                }
            }
        }

        ans = result.get(0);
        return ans;

    }

    /**
     * section 5 - 5 : 쇠막대
     */
    public int ironRod(String str) {

        int ans = 0;
        Stack<Character> rod = new Stack<>();

        for (int i = 0 ; i < str.length() ; i++) {

            char c = str.charAt(i);

            if (c == '(') {
                rod.push(c);
            } else if (str.charAt(i - 1) == '(') {
                rod.pop();
                ans += rod.size();
            } else {
                rod.pop();
                ans++;
            }

        }

        return ans;

    }

    /**
     * section 5 - 6 : 공주 구하기
     */
    public int savePrincess(int n, int k) {

        int ans = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1 ; i <= n ; i++) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {

            for (int i = 1 ; i < k ; i++) {
                queue.offer(queue.poll());
            }

            queue.poll();

            if (queue.size() == 1) {
                ans = queue.poll();
            }
            
        }

        return ans;

    }

    /**
     * section 5 - 7 : 교육과정 설계
     */
    public String isValidTimeTable(String r, String c) {

        String ans = "YES";
        Queue<Character> q = new LinkedList<>();

        for (char re : r.toCharArray()) {
            q.offer(re);
        }

        for (char cl : c.toCharArray()) {

            if (q.contains(cl)) {
                if (cl != q.poll()) {
                    return "NO";
                }
            }

        }

        if (!q.isEmpty()) {
            return "NO";
        }

        return ans;

    }

    /**
     * section 5 - 8 : 응급실
     */
    public int orderOfTreatment(int[] risk, int M) {

        int ans = 0;
        Queue<Person> order = new LinkedList<>();
        for (int i = 0 ; i < risk.length ; i++) {
            order.offer(new Person(i, risk[i]));
        }

        while (!order.isEmpty()) {

            boolean flag = true;
            Person p = order.poll();

            for (Person t : order) {
                if (p.getPriority() > t.getPriority()) {
                    order.offer(p);
                    flag = false;
                    break;
                }
            }

            if (flag) {

                ans++;

                if (p.getId() == M) {
                    return ans;
                }

            }

        }
        return ans;
    }


}
