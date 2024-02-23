import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 유기농 배추
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T, M, N, K, ANSWER;

    static int[][] field;
    static boolean[][] visited;

    public void solution(int x, int y) {

        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        visited[x][y] = true;

        for (int i = 0; i < dx.length; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < M && 0 <= ny && ny < N) {

                if (!visited[nx][ny] && field[nx][ny] == 1) {
                    field[nx][ny] = 0;
                    solution(nx, ny);
                }

            }

        }


    }

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        T = Integer.parseInt(br.readLine());

        for (int rep = 0 ; rep < T ; rep++) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            ANSWER = 0;

            field = new int[M][N];
            visited = new boolean[M][N];

            int x, y;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                field[x][y] = 1;
            }

            for (x = 0; x < M; x++) {
                for (y = 0; y < N; y++) {
                    if (field[x][y] == 1) {
                        main.solution(x, y);
                    }
                }
            }

            for (x = 0; x < M; x++) {
                for (y = 0; y < N; y++) {
                    if (field[x][y] == 1) {
                        ANSWER++;
                    }
                }
            }

            bw.write(ANSWER + "\n");

        }

        bw.close();

    }

}