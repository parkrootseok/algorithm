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

        int T = Integer.parseInt(br.readLine());

        int P, Q, R, S, W;
        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            String[] inputs = br.readLine().split(" ");

            P = Integer.parseInt(inputs[0]);    // 1리터 당 A사 요금
            Q = Integer.parseInt(inputs[1]);    // B사 기본 요금
            R = Integer.parseInt(inputs[2]);    // B사 추가 요금 발생 구간
            S = Integer.parseInt(inputs[3]);    // B사 1리터 요금
            W = Integer.parseInt(inputs[4]);    // 1달 수도량

            int priceA, priceB;

            priceA = W * P;

            priceB = Q;
            if(W > R) {
                priceB += (W - R) * S;
            }

            bw.write(" " + Math.min(priceA, priceB) + "\n");
            bw.flush();

        }

        bw.close();

    }

}