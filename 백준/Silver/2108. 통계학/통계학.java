import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 통계학
 */

public class Main {

    static int N;
    static int[] numbers;
    static int[] cnt;

    public int average() {

        int average = 0;
        for (int i = 0 ; i < N ; i++) {

            average += numbers[i];

        }

        return (int) Math.round((double) average / N);
    }

    public int median() {
        return numbers[Math.round(N / 2)];
    }

    public int mode() {

        int maxCnt = Arrays.stream(cnt).max().getAsInt();

        ArrayList<Integer> result = new ArrayList<>();
        for (int n : numbers) {

            if (maxCnt == cnt[n + 4000] && !result.contains(n)) {
                result.add(n);
            }

        }


        return result.size() >= 2 ? result.get(1) : result.get(0);

    }

    public int range() {

        return numbers[numbers.length - 1] - numbers[0];

    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        N = Integer.parseInt(br.readLine());
        cnt = new int[8001];
        numbers = new int[N];

        for (int i = 0 ; i < N ; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            cnt[numbers[i] + 4000]++;
        }

        Arrays.sort(numbers);

        int average = m.average(), median = m.median(), mode = m.mode(), range = m.range();

        bw.write(average + "\n" + median + "\n" + mode + "\n" + range +"\n");
        bw.close();

    }

}