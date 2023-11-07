import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, K, ANSWER;

    //    public static boolean solution(int time) {
//
//    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            List<Integer> arrived = new ArrayList<>();
            String[] inputs = br.readLine().split(" ");

            N = Integer.parseInt(inputs[0]);    // N명의 사람에게
            M = Integer.parseInt(inputs[1]);    // M초에
            K = Integer.parseInt(inputs[2]);    // K개의 붕어빵

            inputs = br.readLine().split(" ");
            for (String input : inputs) {
                arrived.add(Integer.valueOf(input));
            }

            Collections.sort(arrived);

            boolean flag = true;
            int bread = 0;
            int last = arrived.get(arrived.size() - 1);

            if (arrived.get(0) == 0) {
                flag = false;
            } else {
                for (int s = 1 ; s <= last ; s++) {
                    if(s % M == 0) {
                        bread += K;
                    }
                    if (arrived.contains(s)) {
                        if (bread > 0) {
                            arrived.remove(0);
                            bread--;
                        } else {
                           flag = false;
                           break;
                        }
                    }
                }
            }

            if (flag) {
                bw.write(" " + "Possible" + "\n");
            } else {
                bw.write(" " + "Impossible" + "\n");
            }

        }

        bw.close();

    }

}