package baekjoon.class2.p10989;

/**
 * 수 정렬하기3
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int max = Integer.MIN_VALUE;
        int[] cnt = new int[10001];

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        for (int i = 0 ; i < N ; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            cnt[numbers[i]]++;
        }

//        /* Arrays 클래스 Sort 메소드 이용*/
//        Arrays.sort(numbers);
//        for (int n : numbers) {
//            bw.write(n + "\n");
//        }

        /* 카운트 솔팅 */
        for (int i =  1 ; i <= 10000 ; i++) {

            while (cnt[i] > 0) {
                bw.write(i + "\n");
                cnt[i]--;
            }

        }


        bw.close();

    }

}