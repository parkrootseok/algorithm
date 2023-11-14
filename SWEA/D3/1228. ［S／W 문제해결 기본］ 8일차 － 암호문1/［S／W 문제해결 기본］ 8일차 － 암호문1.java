import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, K;
    static int ANSWER;
    static List<String> secrets;
    static String[] command;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void solution() {

        for (String cmd : command) {

            String[] split = cmd.split(" ");

            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);

            int i = x;
            for (int j = 2 ; j < y + 2; i++, j++) {
                secrets.add(i, split[j]);
            }

        }

        return;

    }

    public static void main(String args[]) throws Exception {

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + i + " ");

            N = Integer.parseInt(br.readLine());
            
            String[] inputs = br.readLine().split(" ");
            secrets = new ArrayList<>();
            for (String input : inputs) {
                secrets.add(input);
            }

            K = Integer.parseInt(br.readLine());

            command = br.readLine().split("I ");
            command = Arrays.copyOfRange(command, 1, command.length);

            solution();

            for (int j = 0; j < 10; j++) {
                bw.write(secrets.get(j) + " ");
            }
            bw.write("\n");

        }

        bw.close();

    }

}