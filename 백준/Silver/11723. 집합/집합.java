import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 집합
 */

public class Main {

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        Set<Integer> set = new HashSet<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0 ; i < n ; i++) {

            int elem = 0;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String cmd = st.nextToken();
            if (!cmd.equals("all") && !cmd.equals("empty")) {
                elem = Integer.parseInt(st.nextToken());
            }

            switch (cmd) {

                case "add":
                    set.add(elem);
                    break;
                case "remove":
                    set.remove(elem);
                    break;
                case "check":
                    if(set.contains(elem)) {
                        bw.write(1 + "\n");
                    } else{
                        bw.write(0 + "\n");
                    }
                    break;
                case "toggle":
                    if (set.contains(elem)) {
                        set.remove(elem);
                    } else {
                        set.add(elem);
                    }
                    break;
                case "all":
                    set.clear();
                    for (int j = 1 ; j <= 20 ; j++) {
                        set.add(j);
                    }
                    break;
                case "empty":
                    set.clear();
                    break;

            }

        }

        bw.close();

    }

}