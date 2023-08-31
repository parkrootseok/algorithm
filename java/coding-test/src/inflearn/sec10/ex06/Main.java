package inflearn.sec10.ex06;

import java.util.Arrays;
import java.util.Scanner;

class Question implements Comparable<Question> {

    private int score;
    private int time;

    public Question(int score, int time) {
        this.score = score;
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public int getTime() {
        return time;
    }

    @Override
    public int compareTo(Question o) {
        return o.time - this.time;
    }
}

/**
 * Section 10 - 6 : 최대 점수 구하기
 */
public class Main {

    static int N, M;
    static Question[] questions;
    static int[] dy;

    public void  solution() {

        dy[0] = 0;

        for (int i = 0 ; i < N ; i++) {

            for (int j = M; j >= questions[i].getTime() ; j--) {

                dy[j] = Math.max(dy[j], dy[j - questions[i].getTime()] + questions[i].getScore());

            }

        }


    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        dy = new int[M + 1];
        Arrays.fill(dy, 0);

        questions = new Question[N];
        for (int i = 0 ; i < N ; i++) {
            questions[i] = new Question(sc.nextInt(), sc.nextInt());
        } Arrays.sort(questions);


        m.solution();
        System.out.println(dy[M]);

    }

}