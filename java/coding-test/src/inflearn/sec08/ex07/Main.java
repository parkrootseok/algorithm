package inflearn.sec08.ex07;

import java.util.Scanner;

/**
 * Section 8 - 7 : 조합수(메모리제이션)
 */
public class Main {

    static int N, R;

    public int solution(int N, int R, int[][] mem) {

        if (mem[N][R] > 0) { // 이미 계산한 값은 연산하지 않고 이용
            return mem[N][R];
        }
        if (N == R || R == 0) {
            return 1;
        }
        return  mem[N][R] = solution(N - 1, R - 1, mem) + solution(N - 1, R, mem);


    }


    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        R = sc.nextInt();

        // 메모리제이션 이용
        int[][] mem = new int[N + 1][R + 1];
        System.out.println(m.solution(N, R, mem));

    }

}