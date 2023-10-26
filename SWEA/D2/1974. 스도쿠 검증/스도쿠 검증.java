import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class Solution {

    static int N, K;
    static String[][] board = new String[10][10];

    public static boolean solution(int cur) {

        Map<String, Integer> row = new HashMap<>();
        Map<String, Integer> col = new HashMap<>();
        Map<String, Integer> grid = new HashMap<>();

        if (cur == 1 || cur == 4 || cur == 7) {

           for (int i = 1; i <= 9 ; i++) {

               if (i % 3 == 1) {
                   grid.clear();
               }

               for (int j = cur; j < cur + 3; j++) {
                   
                   grid.put(board[i][j], grid.getOrDefault(board[i][j], 0) + 1);
                   
                   if (grid.getOrDefault(board[i][j], 0) == 2) {
                       return false;
                   }
                   
               }

           }

        }

        for (int i = 1 ; i <= 9 ; i++) {

            row.put(board[cur][i], row.getOrDefault(board[cur][i], 0) + 1);
            col.put(board[i][cur], col.getOrDefault(board[i][cur], 0) + 1);
            
            if (row.getOrDefault(board[cur][i], 0) == 2 || col.getOrDefault(board[i][cur], 0) == 2) {
                return false;
            }

        }

        return true;

    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        SimpleDateFormat format = new SimpleDateFormat("hh mm");

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            for (int j = 1 ; j <= 9 ; j++) {
                String[] numbers = br.readLine().split(" ");
                int k = 1;
                for (String number : numbers) {
                    board[j][k++] = number;
                }
            }

            String answer = "1";
            for (int j = 1 ; j <= 9 ; j++) {
                if (!solution(j)) {
                    answer = "0";
                    break;
                }
            }

            bw.write(answer + "\n");

        }

        bw.close();

    }

}