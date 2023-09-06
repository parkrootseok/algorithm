import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Main {

    public int[] solution(int n) {
        return IntStream.rangeClosed(0, n).filter(v -> v % 2 == 1).toArray();
    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();

        int[] arr = m.solution(15);
        System.out.println();

    }

}