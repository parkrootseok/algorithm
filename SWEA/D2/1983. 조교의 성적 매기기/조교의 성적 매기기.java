import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        double[] percent = new double[]{0.35, 0.45, 0.2};
        String[] grades = new String[]{"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");
            
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]), K = Integer.parseInt(input[1]);
            
            
            String[] grade = new String[N + 1];
            int cnt = 1;
            for (int j = 0 ; j < 10; j++) {
                for (int k = 0 ; k < N / 10 ; k++) {
                    grade[cnt++] = grades[j];
                }
            }

            Map<Integer, Double> rank = new HashMap<>();
            for (int j = 0; j < N; j++) {
                String[] scores = br.readLine().split(" ");

                double sum = 0;
                for (int k = 0 ; k < scores.length ; k++) {
                    sum += Double.parseDouble(scores[k]) * percent[k];
                }

                rank.put(j + 1, sum);
            }
            
            List<Map.Entry<Integer, Double>> sortRank = new ArrayList<>(rank.entrySet());
            Collections.sort(sortRank, new Comparator<Entry<Integer, Double>>() {
                @Override
                public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });


            int pos = 0;
            for (Entry<Integer, Double> r : sortRank) {
                pos++;
                if (r.getKey() == K) {
                    break;
                }
            }
            
            bw.write(grade[pos] + "\n");
            
        }

        bw.close();

    }

}