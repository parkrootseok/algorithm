package inflearn.sec10.ex04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Brick implements Comparable<Brick> {

    private int size, height, weight;

    public Brick(int size, int height, int weight) {
        this.size = size;
        this.height = height;
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Brick o) {
        return o.size - this.size;
    }
}

/**
 * Section 10 - 4 : 가장 높은 탑 쌓기
 */
public class Main {

    static int ANSWER = 0;
    static int N, size, height, weight;
    static int[] result;
    static ArrayList<Brick> bricks = new ArrayList<>();

    public void  solution() {

        result[0] = bricks.get(0).getHeight();
        ANSWER = result[0];

        for (int i = 1 ; i < bricks.size() ; i++) {

            int max = 0;

            for (int j = i - 1 ; j >= 0 ; j--) {    // i번의 벽돌보다

                // 무게가 큰 벽돌을 탐색 (단, i번째 이전의 벽돌들을 탐색)
                if (bricks.get(j).getWeight() > bricks.get(i).getWeight() && result[j] > max) {
                    max = result[j];    // 무게가 큰 벽돌이 있다면 그 위에 올릴 수 있으므로 초기화
                }

                result[i] = max + bricks.get(i).getHeight();
                ANSWER = Math.max(ANSWER, result[i]);

            }

        }

    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        for (int i = 0 ; i < N ; i++) {

            size = sc.nextInt();
            height = sc.nextInt();
            weight = sc.nextInt();

            bricks.add(new Brick(size, height, weight));
        }

        result = new int[N];
        Collections.sort(bricks);

        m.solution();
        System.out.println(ANSWER);

    }

}