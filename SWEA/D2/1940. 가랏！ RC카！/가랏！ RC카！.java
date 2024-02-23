import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;

    public static void main(String args[]) throws Exception {
        
        final String EQ = "0";
        final String AC = "1";
        final String DE = "2";

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);
            N = Integer.parseInt(br.readLine());

            int speed = 0;
            int distance = 0;
            for (int j = 0; j < N; j++) {

                String[] inputs = br.readLine().split(" ");
                String command = inputs[0];
                
                int number;
                switch (command) {

                    case AC:
                        number = Integer.parseInt(inputs[1]);
                        speed += number;
                        break;
                    case DE:
                        number = Integer.parseInt(inputs[1]);
                        speed -= number;
                        if (speed < 0) {
                            speed = 0;
                        }
                        break;
                    case EQ:
                        break;
                }

                distance += speed;

            }

            bw.write(" " + distance + "\n");
            bw.flush();

        }

        bw.close();

    }

}