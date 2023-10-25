import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

class Solution {

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i + " ");

            ArrayList<Double> numbers = new ArrayList<>();
            String[] input = br.readLine().split(" ");
            for (String n : input){
                numbers.add(Double.parseDouble(n));
            }
            Collections.sort(numbers);

            numbers.remove(0);
            numbers.remove(numbers.size() - 1);

            double sum = 0;
            for (double n : numbers) {
                sum += n;
            }
            sum /= 8;

            bw.write((int) Math.round(sum) + "\n");

        }

        bw.close();

    }

}