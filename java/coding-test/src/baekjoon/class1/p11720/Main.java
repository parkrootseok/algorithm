package baekjoon.class1.p11720;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int sum = 0;
        for (char n : str.toCharArray()) {
            sum += Character.getNumericValue(n);
        }

        System.out.println(sum);

    }


}