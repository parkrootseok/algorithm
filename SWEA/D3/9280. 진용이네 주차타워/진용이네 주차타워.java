import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static int N, M, X, Y;
    static int ANSWER;

    static int POSSIBLE = 0;
    static int IMPOSSIBLE = -1;
    static int[] price;
    static int[] weight;
    
    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static int solution() throws IOException {

        Map<Integer, Integer> parkingLot = new HashMap<>();
        Queue<Integer> waiting = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            parkingLot.put(i, 0);
        }

        for (int i = 0; i < 2 * M; i++) {

            int number = Integer.parseInt(br.readLine());
            int seatNumber;

            if (number > 0) {

                if ((seatNumber = allocateSeat(parkingLot)) != IMPOSSIBLE) {    // 주차공간을 할당

                    if (waiting.isEmpty()) {    // 대기중인 차가 있다면 먼저 주차
                        in(parkingLot, seatNumber, number);
                    } else {    // 그렇지 않다면 바로 주차
                        waiting.offer(number);
                        in(parkingLot, seatNumber, waiting.poll());
                    }
                    
                    // 주차한 차에 대한 요금 계산
                    ANSWER += fee(seatNumber, number);

                } else {    // 주차 불가
                    waiting.offer(number); // 대기
                }

            } else {

                out(parkingLot, Math.abs(number));  // 출차

                if (!waiting.isEmpty()) {   // 확보된 주차공간으로 할당
                    seatNumber = allocateSeat(parkingLot);
                    number = waiting.poll();
                    in(parkingLot, seatNumber, number);
                    ANSWER += fee(seatNumber, number);
                }

            }

        }

        return ANSWER;

    }

    public static int fee(int s, int n) {
        return price[s] * weight[n];
    }

    public static void in(Map<Integer, Integer> parkingLot, int s, int n) {
        parkingLot.put(s, n);
    }

    public static void out(Map<Integer, Integer> parkingLot, int n) {
        for (Entry<Integer, Integer> e : parkingLot.entrySet()) {
            if (e.getValue() == n) {
                parkingLot.put(e.getKey(), POSSIBLE);
                return;
            }
        }
    }

    public static int allocateSeat(Map<Integer, Integer> parkingLot) {

        for (Entry<Integer, Integer> e : parkingLot.entrySet()) {
            if (e.getValue() == POSSIBLE) {
                return e.getKey();
            }
        }

        return IMPOSSIBLE;

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            ANSWER = 0;
            bw.write("#" + i + " ");

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            M = Integer.parseInt(inputs[1]);

            price = new int[N];
            for (int j = 0; j < N; j++) {
                price[j] = Integer.parseInt(br.readLine()); // n개의 공간에 대한 요금
            }

            weight = new int[M + 1];
            for (int j = 1; j <= M; j++) {
                weight[j] = Integer.parseInt(br.readLine());
            }

            ANSWER = solution();
            bw.write(ANSWER + "\n");

        }

        bw.close();

    }

}