import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 설탕 배달
 */

public class Main {


    public int solution(int n) {

        // 불가능한 경우
        if (n == 4 || n == 7) {
            return -1;
        }

        // 5의 배수인 경우
        if (n % 5 == 0) {
            return (n / 5);
        }

        // 나머지가 1 또는 3인 경우 [ 6, 8, 11, 13, 16, ... , (x / 5) + 1, (x / 5) + 3) ]
        // n을 5로 나눈 몫의 1개를 더한것과 같음
        if (n % 5 == 1 || n % 5 == 3)   {
            return (n / 5) + 1;
        }


        // 나머지가 2 또는 4인 경우 [ 9, 12, 14, 17, 19, ... , (x / 5) + 2, (x / 5) + 4) ]
        // n을 5로 나눈 몫의 2개를 더한것과 같음
        if (n % 5 == 2 || n % 5 == 4)   {
            return (n / 5) + 2;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Main m = new Main();

        int n = Integer.parseInt(br.readLine());
        bw.write( m.solution(n) + "");
        bw.close();

    }

}