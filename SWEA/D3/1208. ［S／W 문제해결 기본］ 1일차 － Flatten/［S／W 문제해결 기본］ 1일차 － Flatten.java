import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, ANSWER;
    static List<Integer> box = new ArrayList<>();

    public static void solution() {

        int last = box.size() - 1;

        for (int i = 0 ; i < N ; i++) {

            box.add(0, box.remove(0) - 1);
            box.add(last, box.remove(last) + 1);
            Collections.sort(box, Collections.reverseOrder());
            ANSWER = Math.min(ANSWER, (box.get(0) - box.get(last - 1)));

        }

    }

    public static void main(String args[]) throws Exception {
        
        for (int i = 1; i <= 10; i++) {

            bw.write("#" + i);

            N = Integer.parseInt(br.readLine());
            String[] inputs = br.readLine().split(" ");

            for (String input : inputs) {
                box.add(Integer.valueOf(input));
            }
            Collections.sort(box, Collections.reverseOrder());

            ANSWER = Integer.MAX_VALUE;
            solution();
            box.clear();

            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}