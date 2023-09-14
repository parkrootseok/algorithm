package baekjoon.class2.p11866;

/**
 * 요세푸스 문제 0
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for (int i = 1 ; i <= N ; i++) {
            queue.offer(i);
        }

        while (queue.size() > 1) {

            for (int i = 0 ; i < K - 1 ; i++) {

                int cur = queue.poll();
                queue.offer(cur);

            }

            sb.append(queue.poll() + ", ");

        } sb.append(queue.poll() + ">");

//        ArrayList<Integer> log = new ArrayList<>();
//        boolean[] killed = new boolean[N];
//
//        int rep = 0;
//        int cur = 0;
//        while (rep < N) {
//
//            cur %= N;
//
//            int cnt = 0;
//            for (int j = cur; cnt != K ; j++) {
//
//                if (!killed[j % N]) {
//                    cur = j % N;
//                    cnt++;
//                }
//
//            }
//
//            killed[cur] = true;
//            log.add(cur + 1);
//            rep++;
//
//        }

        System.out.println(sb.toString());

    }

}