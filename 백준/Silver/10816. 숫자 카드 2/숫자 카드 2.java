
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

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[20000001];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N ; i++) {

            int num = Integer.parseInt(st.nextToken());

            numbers[num + 10000000]++;
        }

        int M = Integer.parseInt(br.readLine());
        int[] find = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M ; i++) {

            int f = Integer.parseInt(st.nextToken());
            find[i] = numbers[f + 10000000];
            bw.write(find[i] + " ");
        }

        bw.close();

    }

}