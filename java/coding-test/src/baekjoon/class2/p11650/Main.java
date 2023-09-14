package baekjoon.class2.p11650;

/**
 * 좌표 정렬하기
 */

import inflearn.sec05.solution.Person;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Coordinate implements Comparable<Coordinate> {

        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Coordinate o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            coordinates.add(new Coordinate(x, y));
        }
        Collections.sort(coordinates);

        for (Coordinate c : coordinates) {
            bw.write(c.x + " " + c.y + "\n");
        }

        bw.close();

    }

}