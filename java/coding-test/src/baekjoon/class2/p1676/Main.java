package baekjoon.class2.p1676;

/**
 * 팩토리얼 0의 개수
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int answer = 0;

        // n의 0의 갯수는 n의 소인수분해에 결과에서 2 * 5 의 갯수와 같음
        // 2의 갯수가 5보다 많은 부분을 이용하여 n!에서 5가 곱해지는 횟수를 카운트
        // n을 5로 나눈 몫이 5의 갯수가 되고, 5의 갯수가 5의 배수라는 것은
        // 5의 제곱인 수가 포함된 갯수 이므로 카운트에 포함
        while (n >= 5) {
            answer += n / 5;
            n /= 5;
        }

        System.out.println(answer);

    }

}