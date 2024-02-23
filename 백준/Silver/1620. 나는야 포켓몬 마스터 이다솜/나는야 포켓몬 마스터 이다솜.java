import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 나는야 포켓몬 마스터 이다솜
 */

public class Main {

    public static void main(String[] args) throws IOException {

        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<Integer, String> book1 = new HashMap<>();
        HashMap<String, Integer> book2 = new HashMap<>();
        for (int i = 0 ; i < n ; i++) {
            String name =  br.readLine();
            book1.put(i + 1, name);
            book2.put(name, i + 1);
        }

        for (int i = 0 ; i < m ; i++) {

            String s = br.readLine();

            if (s.matches("[0-9]+")) {
                bw.write(book1.get(Integer.parseInt(s)) + "\n");
            } else {
                bw.write(book2.get(s) + "\n");
            }

        }


        bw.close();

    }

}