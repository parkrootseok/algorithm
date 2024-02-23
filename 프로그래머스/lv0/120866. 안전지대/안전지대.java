import java.util.*;

class Solution {
    public int solution(int[][] board) {

        int[] dx = {1, 0, -1 ,0, 1, -1, 1, -1};
        int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};

        int SAFE = 0, TRAP = 1, UNSAFE = 2;
        for (int i = 0 ; i < board.length ; i++) {

            for (int j = 0 ; j < board[i].length ; j++) {

                if (board[i][j] == TRAP) {

                    for (int k = 0 ; k < dx.length ; k++) {

                        int x = i - dx[k];
                        int y = j - dy[k];

                        if (0 <= x && x < board.length && 0 <= y && y < board.length) {

                            if (board[x][y] == SAFE) {
                                board[x][y] = UNSAFE;
                            }

                        }

                    }

                }

            }

        }

        return (int) Arrays.stream(board)
                .flatMapToInt(arr -> Arrays.stream(arr))
                .filter(i -> i == SAFE)
                .count();

    }
}