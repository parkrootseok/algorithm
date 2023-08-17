package sec08.ex01;

import java.util.Scanner;

/**
 * Section 8 - 1 : 합이 같은 부분집합
 */
public class Main {

    static String ANSWER = "NO";
    static int N, total = 0;
    boolean flag = false;

    public void solution(int start, int sum, int[] elements) {

        if (flag) {
            return;
        }

        if (sum > total / 2) {
            return;
        }

        if (start == N) {
            if ((total - sum) == sum) {
                flag = true;
                ANSWER = "YES";
            }
        }

        solution(start + 1, sum + elements[start], elements);
        solution(start + 1, sum, elements);
    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int[] elements = new int[N];
        for (int i = 0; i < N; i++) {
            elements[i] = sc.nextInt();
            total += elements[i];
        }

        m.solution(0, 0, elements);
        System.out.println(ANSWER);
    }
}