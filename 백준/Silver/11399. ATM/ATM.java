import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * ATM
 */

public class Main {

    public static void main(String[] args) throws IOException {

        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] time = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int i = 0;
        while (st.hasMoreTokens()) {
            time[i++] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(time);
        
        int sum = 0, answer = 0;
        for (i = 0 ; i < N ; i++) {
            
            sum += time[i];
            answer += sum;
            
            
        }

        bw.write(answer + "\n");
        bw.close();

    }

}