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
    static int ANSWER, RADIUS;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void solution() {

        int square = RADIUS * RADIUS;
        
        // 1사분면에서 만족하는 좌표를 카운트
        for (int x = 0; x <= RADIUS; x++) {
            for (int y = 0; y <= RADIUS; y++) {
                if ((x * x) + (y * y) <= square) {
                    ANSWER++;
                }
            }
        }
    
        // 1사분면의 좌표에 대하여 x, y, 원점 대칭인 좌표의 개수를 세기위하여 * 4
        // 겹치는 부분을 제거하기 위해 RADIUS(반지름 길이만큼) * 4(총 4번의 중복 좌표 발생)
        // 원점은 1번만 반영
        ANSWER = (ANSWER * 4) - (RADIUS * 4) - 3;
    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            ANSWER = 0;
            bw.write("#" + i + " ");
            RADIUS = Integer.parseInt(br.readLine());
            solution();
            bw.write(ANSWER + "\n");

        }

        bw.close();

    }

}