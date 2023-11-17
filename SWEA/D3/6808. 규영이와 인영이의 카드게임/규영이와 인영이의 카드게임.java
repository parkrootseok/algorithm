import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static int N, M, X, Y;
    static int WIN, LOSE;

    static int CARD_NUMBER = 9;

    static boolean count[];

    static int remainCards[];
    static int gu[];
    static int in[];

    static boolean visited[];

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void solution(int depth) {

        if (depth == CARD_NUMBER) {   // 인영이의 카드리스트가 완성되면 점수를 계산

            int myScore = 0, yourScore = 0;
            for (int i = 0; i < CARD_NUMBER; i++) {

                if (gu[i] < in[i]) {
                    yourScore += (gu[i] + in[i]);
                }

                if (gu[i] > in[i]) {
                    myScore += (gu[i] + in[i]);
                }
            }

            if (myScore > yourScore) {
                WIN++;
            } else if (myScore < yourScore) {
                LOSE++;
            }
            return;
        }

        for (int i = 0; i < CARD_NUMBER; i++) { // 규영이가 뽑지 않은 카드로 중복되지 않는 수열(인영이의 카드리스트)을 생성

            if (!visited[i]) {
                visited[i] = true;
                in[depth] = remainCards[i];
                solution(depth + 1);
                visited[i] = false;
            }


        }

    }


    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            count = new boolean[19];    // 규영이가 뽑은 카드를 체크
            gu = new int[CARD_NUMBER];  // 규영이의 카드 리스트
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < CARD_NUMBER; j++) {
                gu[j] = Integer.parseInt(inputs[j]);
                count[gu[j]] = true;    // 규영이가 가지고 있는 카드를 체크
            }

            remainCards = new int[CARD_NUMBER]; // 규영이가 뽑고 남은 카드 목록
            int k = 0;
            for (int j = 1; j <= 18; j++) {
                if (!count[j]) {    // 규영이가 가지고 있지 않은 카드만 남은 카드 목록에 삽입
                    remainCards[k++] = j;
                }
            }

            in = new int[CARD_NUMBER];
            visited = new boolean[CARD_NUMBER];
            WIN = LOSE = 0;
            solution(0);

            bw.write(WIN + " " + LOSE + "\n");

        }

        bw.close();

    }

}