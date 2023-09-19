package baekjoon.class2.p10816;

/**
 * 숫자 카드 2
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static class Person implements Comparable<Person> {

        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return this.age - o.age;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        // 카운팅 솔트를 활용하기 위해 나올수 있는 숫자의 범위가 -10,000,000 ~ 10,000,000 이므로 크기가 20,000,001인 배열 선언
        int[] numbers = new int[20000001];

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N ; i++) {
            // 입력되는 숫자를 인덱스로 활용하여 증가 연산자 실행
            // 해싱 수행(+ 10,000,000) 후 인덱스로 활용
            // 즉, -10,000,000은 0이고, 10,000,000은 20,000,000
            numbers[Integer.parseInt(st.nextToken()) + 10000000]++;
        }

        int M = Integer.parseInt(br.readLine());
        int[] find = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M ; i++) {
            find[i] = numbers[Integer.parseInt(st.nextToken()) + 10000000];
            bw.write(find[i] + " ");
        }

        bw.close();

    }

}