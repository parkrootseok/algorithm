import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 듣보잡
 */

public class Main {

    public static void main(String[] args) throws IOException {

        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String name;
        Map<String, Integer> log = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0 ; i < n ; i++) {

           name = br.readLine();
           log.put(name, log.getOrDefault(name, 0) + 1);

        }

        for (int i = 0 ; i < m ; i++) {

            name = br.readLine();

            int v = log.getOrDefault(name, 0) + 1;
            log.put(name, v);
            if (v > 1) {
               result.add(name);
            }

        }

        Collections.sort(result);
        bw.write(result.size() + "\n");
        for (String s : result) {
            bw.write(s + "\n");
        }

        bw.close();

    }

}
