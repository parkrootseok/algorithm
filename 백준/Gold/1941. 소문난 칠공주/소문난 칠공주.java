import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_소문난칠공주
 *
 * @author parkrootseok
 */
public class Main {

    public static class Node {

        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static char SOM = 'S';


    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static char[][] map;
    public static int[] selected;
    public static int count;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        map = new char[5][5];
        for (int row = 0; row < 5; row++) {
            char[] inputs = br.readLine().trim().toCharArray();
            for (int col = 0; col < 5; col++) {
                map[row][col] = inputs[col];
            }
        }

        selected = new int[25];
        combination(0, 0, 0);

        sb.append(count);
        bw.write(sb.toString());
        bw.close();

    }

    public static void combination(int depth, int nextIndex, int yeonCount) {

        if (4 <= yeonCount) {
            return;
        }

        if (depth == 7) {

            if (bfs() == 7) {
                count++;
            }

            return;
        }

        for (int index = nextIndex; index < 25; index++) {

            selected[depth] = index;

            if (map[index / 5][index % 5] == SOM) {
                combination(depth + 1, index + 1, yeonCount);
            } else {
                combination(depth + 1, index + 1, yeonCount + 1);
            }

        }

    }

    public static int bfs() {

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(selected[0] / 5, selected[0] % 5));

        int curCount = 1;
        boolean[] isVisited = new boolean[7];
        isVisited[0] = true;

        while (!queue.isEmpty()) {

            Node node = queue.poll();

            for (int dir = 0; dir < dr.length; dir++) {

                int nRow = node.row + dr[dir];
                int nCol = node.col + dc[dir];

                if (nRow < 0 || 5 <= nRow || nCol < 0 || 5 <= nCol) {
                    continue;
                }

                // 선택한 좌표에 속하는지 확인
                for (int idx = 1; idx < 7; idx++) {

                    // 선택한 좌표이면서, 처음 방문할 때
                    if (selected[idx] == (nRow * 5 + nCol) && !isVisited[idx]) {

                        // 방문 처리
                        isVisited[idx] = true;

                        queue.add(new Node(nRow, nCol));

                        // 카운팅
                        curCount++;

                        break;

                    }

                }

            }

        }

        return curCount;

    }

}