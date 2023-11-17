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

    static int OPEN = 1;
    static int timeTable[];

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static int solution(int n, int start) {

        int i, j;
        for (i = start, j = 0; n > 0; i++, j++) {

            i %= 7; // 일주일 단위이므로 7로 나머지 연산

            if (timeTable[i] == OPEN) {
                n--;
            }

        }

        return j; 

    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            N = Integer.parseInt(br.readLine());    // 듣고 싶은 횟수

            timeTable = new int[7];
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < 7; j++) {
                timeTable[j] = Integer.parseInt(inputs[j]);
            }

            ANSWER = Integer.MAX_VALUE;
            for (int j = 0; j < 7; j++) {

                if (timeTable[j] == OPEN) { // 수업이 있는 날을 개강일로 생각
                    ANSWER = Math.min(ANSWER, solution(N, j));
                }

            }

            bw.write(ANSWER + "\n");

        }

        bw.close();

    }

}