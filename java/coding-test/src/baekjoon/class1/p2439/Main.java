package baekjoon.class1.p2439;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine().toUpperCase();

        HashMap<Character, Integer> result = new HashMap<>();
        for (char n : str.toCharArray()) {

            if (!result.containsKey(n)) {
                result.put(n, result.getOrDefault(n, 0) + 1);
            } else {
                result.put(n, result.get(n) + 1);
            }

        }

        char ANSWER = '?';
        int max = Integer.MIN_VALUE;
        for (char n : result.keySet()) {

            if (max < result.get(n)) {
                max = result.get(n);
                ANSWER = n;
            } else if (max == result.get(n)) {
                ANSWER = '?';
            }

        }

        System.out.println(ANSWER);

    }

}