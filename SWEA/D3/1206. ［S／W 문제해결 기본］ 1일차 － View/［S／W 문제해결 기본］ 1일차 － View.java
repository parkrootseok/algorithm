import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static int[] height;

    public static int solution(int cur) {

        int lMax = Math.max(height[cur - 2], height[cur - 1]);
        int rMax = Math.max(height[cur + 2], height[cur + 1]);

        if (rMax >= height[cur] || lMax >= height[cur]) {
            return 0;
        }

        int max = Math.max(lMax, rMax);
        return height[cur] - max;

    }

    public static void main(String args[]) throws Exception {

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + i + " ");
            N = Integer.parseInt(br.readLine());

            height = new int[N];
            String[] inputs = br.readLine().split(" ");
            for (int j = 0; j < N ;j++) {
                height[j] = Integer.parseInt(inputs[j]);
            }

            int answer = 0;
            for (int j = 2 ; j < N - 2 ; j++) {
                answer += solution(j);
            }

            bw.write(answer + "\n");

        }

        bw.close();

    }

}