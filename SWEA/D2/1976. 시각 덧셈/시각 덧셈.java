import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {

    static int N, K;
    
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        
        for (int i = 1; i <= T; i++) {
            
            bw.write("#" + i + " ");
            String[] time = br.readLine().split(" ");
            
            int m = Integer.parseInt(time[1]) + Integer.parseInt(time[3]);
            int h = Integer.parseInt(time[0]) + Integer.parseInt(time[2]);
            
            if (m >= 60) {
                m -= 60;
                h++;
            }
            
            if (h > 12) {
                h -= 12;
            }
            
            bw.write(h + " " + m);
            bw.write("\n");
            
        }

        bw.close();

    }

}