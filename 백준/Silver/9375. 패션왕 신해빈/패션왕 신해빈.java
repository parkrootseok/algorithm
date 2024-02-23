import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 패션왕 신해빈
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;

    public static void main(String[] args) throws IOException {

        Main main = new Main();

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            HashMap<String, Integer> clothes = new HashMap<>();
            M = Integer.parseInt(br.readLine());

            for (int j = 1 ; j <= M ; j++) {

                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                st.nextToken();
                String type = st.nextToken();

                clothes.put(type, clothes.getOrDefault(type, 0) + 1);

            }

            int answer = 1;

            for (int v : clothes.values()) {
                answer *= (v + 1);
            }

            bw.write(answer - 1 + "\n");

        }



        bw.close();

    }

}