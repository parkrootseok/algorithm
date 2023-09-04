package baekjoon.class1.p3052;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        TreeSet<Integer> result = new TreeSet<>();
        for (int i = 0 ; i < 10 ; i++) {

            int num = Integer.parseInt(br.readLine()) % 42;
            result.add(num);

        }

        System.out.println(result.size());

    }

}