package baekjoon.class2.p10814;

/**
 * 나이순 정렬
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

        StringBuilder[] sb = new StringBuilder[201];
        for (int i = 1; i < sb.length ; i++) {
            sb[i] = new StringBuilder();
        }

        ArrayList<Person> member = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
//            member.add(new Person(age, name));
            sb[age].append(age + " " + name + "\n");
        }
//        Collections.sort(member);

//        for (Person p : member) {
//            bw.write(p.age + " " + p.name + "\n");
//        }

        for (int i = 1 ; i < sb.length ; i++) {

            if (sb[i].length() > 0) {
                bw.write(sb[i].toString());
            }

        }

        bw.close();

    }

}