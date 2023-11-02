import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Ingredient implements Comparable<Ingredient> {

    int score;
    int cal;

    public Ingredient(String score, String cal) {
        this.score = Integer.parseInt(score);
        this.cal = Integer.parseInt(cal);
    }

    @Override
    public int compareTo(Ingredient o) {
        return o.cal - this.cal;
    }

}

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    static int N, L, ANSWER;
    static int[] dp;
    static Ingredient[] ingredients;

    public static void knapsack() {

        for (int i = 0 ; i < N ; i++) {

            for (int j = L ; j >= ingredients[i].cal ; j--) {

                dp[j] = Math.max(dp[j], dp[j - ingredients[i].cal] + ingredients[i].score);

            }

        }
    }

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);
            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            L = Integer.parseInt(inputs[1]);

            dp = new int[L+1];
            ingredients = new Ingredient[N];
            for (int j = 0 ; j < N ; j++) {
                inputs = br.readLine().split(" ");
                ingredients[j] =  new Ingredient(inputs[0], inputs[1]);
            }
            
            Arrays.sort(ingredients);
            knapsack();
            bw.write(" "  + dp[L] + "\n");

        }

        bw.close();

    }

}