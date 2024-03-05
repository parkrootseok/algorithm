import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_20056_마법사상어와파이어볼
 * @author parkrootseok
 *
 * - 파이어볼
 *  - M개 발사
 *  - 위치(row, col), 질량, 방향, 속력
 *  - 방향은 어떤칸과 인접한 곳을 의미
 *   - 위부터 시계방향
 *
 * - 격자
 *  - 1번 행은 N번 행과 연결
 *  - 1번 열은 N번 열과 연결
 *
 * - 명령
 *  - 모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동
 *    - 동일한 선상에 여러 파이어볼 가능
 *  - 이동이 끝난 뒤, 2개 이상의 파이어볼이 있는 칸은 다음 로직을 수행
 *    - 파이어볼은 모두 하나로 합쳐지고
 *    - 4개의 파이어볼로 나눈다
 *     - 4개의 파이어볼의 질량, 속력, 방향은 다음과 같이 설정
 *       - 질량 : 질량 합 / 5
 *       - 속력 : 속력 합 / 총 개수
 *       - 방향 : 모두 홀수 또는 짝수이면 0, 2, 4, 6 아니면 1, 3, 5, 7
 *    - 질량이 0이라면 없어짐
 *
 * - 명령을 수행하고 남은 파이어볼의 질량을 구하자
 *
 *
 * 1. 입력 받기
 *  1-1. 맵 크기, 파이어볼 갯수, 명령 횟수를 받는다.
 *  1-2. 파이어볼에 대한 정보를 받는다.
 * 2. 명령을 수행
 *  2-1. 모든 파이어 볼을 이동
 *  2-2. 이동한 파이어 볼의 위치를 확인
 *  2-3. 2개 이상 파이어볼이 있는 칸 확인
 *   2-3-1. 2개 이상이 아니라면 스킵
 *   2-3-2. 2개 이상이 라면 해당 칸에 있는 파이어볼을 4개로 나눈다.
 *    3. 존재하는 파이어볼의 질량, 속력, 방향을 다시 설정
 *     3-1. 동일한 곳에 존재하는 파이어볼 삭제
 *     3-2. 질량이 0이라면 새로운 파이어볼을 생성하지 않고 종료
 *     3-3. 질량이 0이 아니라면 4개의 새로운 파이어 볼을 생성
 **/

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static String[] inputs;
    static String input;

    // 상, 우상, 우, 하우, 하, 하좌, 좌, 좌상
    static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

    static class FireBall {

        int row;
        int col;
        int mass;
        int speed;
        int direction;

        FireBall(int row, int col, int mass, int speed, int direction) {
            this.row = row;
            this.col = col;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }

        void move() {

            int nextRow = row + (dr[direction] * speed);
            int nextCol = col + (dc[direction] * speed);

            if (nextRow >= mapSize) {
                nextRow %= mapSize;
            }

            if (nextCol >= mapSize) {
                nextCol %= mapSize;
            }

            while(nextRow < 0) {
                nextRow = (mapSize - Math.abs(nextRow));
            }

            while(nextCol < 0) {
                nextCol = (mapSize - Math.abs(nextCol));
            }

            this.row = nextRow;
            this.col = nextCol;

        }


    }

    static int[][] map;
    static int mapSize;
    static int fireBallNumber;
    static int commandNumber;

    static List<FireBall> fireBalls;

    public static void input() throws IOException {

        // 1-1. 맵 크기, 파이어볼 갯수, 명령 횟수를 받는다.
        inputs = br.readLine().trim().split(" ");
        mapSize = Integer.parseInt(inputs[0]);
        fireBallNumber = Integer.parseInt(inputs[1]);
        commandNumber = Integer.parseInt(inputs[2]);

        // 1-2. 파이어볼에 대한 정보를 받는다.
        fireBalls = new ArrayList<>();
        for (int idx = 0; idx < fireBallNumber; idx++) {

            inputs = br.readLine().trim().split(" ");

            int row = Integer.parseInt(inputs[0]);
            int col = Integer.parseInt(inputs[1]);
            int mass = Integer.parseInt(inputs[2]);
            int speed = Integer.parseInt(inputs[3]);
            int direction = Integer.parseInt(inputs[4]);

            fireBalls.add(new FireBall(row - 1, col - 1, mass, speed, direction));

        }

    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        // 1. 입력 받기
        input();

        // 2. 명령을 수행
        for (int command = 0; command < commandNumber; command++) {

            // 2-1. 모든 파이어 볼을 이동
            for (FireBall fb : fireBalls) {
                fb.move();
            }

            // 2-2. 이동한 파이어 볼의 위치를 확인
            int[][] count = new int[mapSize][mapSize];
            for (FireBall fb : fireBalls) {
                count[fb.row][fb.col]++;
            }

            // 2-3. 2개 이상 파이어볼이 있는 칸 확인
            for (int row = 0; row < mapSize; row++) {

                for (int col = 0; col < mapSize; col++) {

                    // 2-3-1. 2개 이상이 아니라면 스킵
                    if (count[row][col] < 2) {
                        continue;
                    }

                    // 2-3-2. 2개 이상이 라면 해당 칸에 있는 파이어볼을 4개로 나눈다.
                    divide(row, col);

                }

            }

        }


        // 4. 남아있는 모든 파이어볼의 질량의 합을 출력
        int total = 0;
        for (FireBall fb : fireBalls) {
            total += fb.mass;
        }

        sb.append(total).append("\n");
        bw.write(sb.toString());
        bw.close();

    }

    public static void divide(int row, int col) {

        List<FireBall> curPositionFireBalls = new ArrayList<>();

        int[][] direction = {
            {0, 2, 4, 6}, // 모두 홀수 또는 짝수일 때
            {1, 3, 5, 7}  // 아닐때
        };

        boolean isEven = false;
        boolean isOdd = false;
        int totalMass = 0;
        int totalSpeed = 0;
        int totalDirection = 0;

        for(FireBall fb : fireBalls) {

            if (fb.row == row && fb.col == col) {
                curPositionFireBalls.add(fb);
                totalMass += fb.mass;
                totalSpeed += fb.speed;

                if ((fb.direction & 1) == 0) {
                    isEven = true;
                }

                else {
                    isOdd = true;
                }

            }

        }

        // 3. 존재하는 파이어볼의 질량, 속력, 방향을 다시 설정

        // - 질량 : 질량 합 / 5
        totalMass /= 5;

        // - 속력 : 속력 합 / 총 개수
        totalSpeed /= curPositionFireBalls.size();

        // - 방향 : 모두 홀수 또는 짝수이면 0, 2, 4, 6 아니면 1, 3, 5, 7
        if (isEven && isOdd) {
            totalDirection = 1;
        }

        else {
            totalDirection = 0;
        }

        // 3-1. 동일한 곳에 존재하는 파이어볼 삭제
        for (FireBall fb : curPositionFireBalls) {
            fireBalls.remove(fb);
        }

        // 3-2. 질량이 0이라면 새로운 파이어볼을 생성하지 않고 종료
        if (totalMass == 0) {
            return;
        }

        // 3-3. 질량이 0이 아니라면 4개의 새로운 파이어 볼을 생성
        for (int piece = 0; piece < 4; piece++) {
            fireBalls.add(new FireBall(row, col, totalMass, totalSpeed, direction[totalDirection][piece]));
        }

    }

}