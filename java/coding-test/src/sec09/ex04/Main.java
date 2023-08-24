package sec09.ex04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

class Lecture implements Comparable<Lecture> {

    private int money;
    private int day;

    public Lecture(int money, int day) {
        this.money = money;
        this.day = day;
    }

    public int getMoney() {
        return money;
    }

    public int getDay() {
        return day;
    }

    @Override
    public int compareTo(Lecture o) {

        return o.day - this.day;

    }

}

/**
 * Section 9 - 4 : 최대 수입 스케쥴
 */
public class Main {

    static int ANSWER = 0;

    static int N, maxDay = Integer.MIN_VALUE;
    static ArrayList<Lecture> lectures = new ArrayList<>();
    static PriorityQueue<Integer> result = new PriorityQueue<>(Collections.reverseOrder());

    public void solution() {

        int j = 0;

        // 날짜는 가장 큰 날짜부터 탐색 시작 (가장 큰 날짜에 할 수 있는 강연은 더 작은 날짜에도 강연이 가능함을 활용)
        for (int i = maxDay ; i >= 1 ; i--) {

            while (j < N) {     // i번째 날에 해당하는 강의료를 구해서

                if (lectures.get(j).getDay() < i)  {
                    break;
                }
                result.offer(lectures.get(j++).getMoney());

            }

            if (!result.isEmpty()) {    // 가장 큰 강의료를 선택하여 더함
                ANSWER += result.poll();
            }

        }



    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int money, day;
        for (int i = 0; i < N; i++) {
            money = sc.nextInt();
            day = sc.nextInt();

            lectures.add(new Lecture(money, day));
            maxDay = Math.max(maxDay, day);

        }
        Collections.sort(lectures);

        m.solution();
        System.out.println(ANSWER);

    }
}