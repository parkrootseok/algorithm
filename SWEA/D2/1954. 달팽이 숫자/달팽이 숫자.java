import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[][] numbers;

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + "\n");

            N = Integer.parseInt(br.readLine());
            int[][] numbers = new int[N][N];
            
            int dic = 1, num = 1, x = 0, y = -1, rep = N;
            while (num <= N * N) {

                for (int j = 0 ; j < rep ; j++) {
                    y += dic;
                    numbers[x][y] = num++;
                }

                rep--;

                for (int j = 0 ; j < rep ; j++) {
                    x += dic;
                    numbers[x][y] = num++;
                }

                dic *= -1;

            }

            for (int j = 0 ; j < N ; j++) {
                
                for (int k = 0 ; k < N ; k++) {
                    
                    bw.write(numbers[j][k] + " ");
                }
                
                
                bw.write("\n");
            }

            bw.flush();

        }

        bw.close();

    }

}