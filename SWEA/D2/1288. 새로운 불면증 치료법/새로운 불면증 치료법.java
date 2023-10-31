import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);
            String numbers = br.readLine();

            int mul = 1;
            Map<Character, Integer> visited = new HashMap<>();
            while (true) {

                String mulNum = String.valueOf(Integer.parseInt(numbers) *  mul++);

                for (char n : mulNum.toCharArray()) {
                  visited.put(n, visited.getOrDefault(n, 0) + 1);
                }

                if(visited.size() == 10) {
                    bw.write(" " + mulNum + "\n");
                    break;
                }

            }

            bw.flush();

        }

        bw.close();

    }

}