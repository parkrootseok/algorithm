import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);

    static int N, K, X, Y;
    static int ANSWER;

    static List<String> secrets;
    static String[] extraSecrets;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void solution(String[] tokens) {
        
        String cmd;
        for (int k = 0; k < tokens.length; k++) {

            int j = 0;
            cmd = tokens[k++];

            switch (cmd) {
            	
                case "I":
                    
                	X = Integer.parseInt(tokens[k++]);
                    Y = Integer.parseInt(tokens[k++]);
                    
                    extraSecrets = new String[Y];
                    
                    for (j = k; j < k + Y; j++) {
                        extraSecrets[j - k] = tokens[j];
                    }
                    
                    k += Y - 1;
                    
                    insert();
                    
                    break;
                    
                case "D":
                	
                    X = Integer.parseInt(tokens[k++]);
                    Y = Integer.parseInt(tokens[k]);
                    
                    delete();
                    
                    break;
                    
                case "A":
                	
                    Y = Integer.parseInt(tokens[k++]);
                    extraSecrets = new String[Y];
                    
                    for (j = k; j < k + Y; j++) {
                        extraSecrets[j - k] = tokens[j];
                    }
                    
                    k += Y - 1;
                    
                    add();
                    
                    break;
                    
            }

        }
        
    }

    public static void insert() {

        for (int i = 0 ; i < Y; i++) {
            secrets.add(X + i, extraSecrets[i]);
        }

    }

    public static void delete() {

        for (int i = 0 ; i < Y; i++) {
            secrets.remove(X);
        }

    }

    public static void add() {

        for (int i = 0 ; i < Y; i++) {
            secrets.add(extraSecrets[i]);
        }

    }


    public static void main(String args[]) throws Exception {

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + i + " ");

            N = Integer.parseInt(br.readLine());

            String[] inputs = br.readLine().split(" ");
            
            secrets = new ArrayList<>();
            
            for (String input : inputs) {
                secrets.add(input);
            }

            K = Integer.parseInt(br.readLine());
            String[] tokens = br.readLine().split(" ");
            
            solution(tokens);

            for (int j = 0; j < 10; j++) {
                bw.write(secrets.get(j) + " ");
            }
            
            bw.write("\n");

        }

        bw.close();

    }

}