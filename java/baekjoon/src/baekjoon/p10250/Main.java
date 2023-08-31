package baekjoon.p10250;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < T ; i++) {

            int XXYY = 0;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int h = Integer.parseInt(st.nextToken());   // 호텔 층 수
            int w = Integer.parseInt(st.nextToken());   // 층마다 방수
            int n = Integer.parseInt(st.nextToken());   // 도착 순서


            int floor;
            if (n % h == 0) {
                floor = h;
            } else {
                floor = (n % h) * 100;
            }

            int roomNum = n / h;
            if (n % h != 0) {
                roomNum += 1;
            }

            XXYY = floor + roomNum;
            bw.write(XXYY + "\n");

        }

        bw.close();

    }

}