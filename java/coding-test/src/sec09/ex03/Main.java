package sec09.ex03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Guest implements Comparable<Guest> {

    private int time;
    private char state;

    public Guest(int time, char state) {
        this.time = time;
        this.state = state;
    }

    public int getTime() {
        return time;
    }

    public int getState() {
        return state;
    }

    @Override
    public int compareTo(Guest o) {
        if (this.time == o.time) {
            return this.state - o.state;
        } else {
            return this.time - o.time;
        }
    }

}

/**
 * Section 9 - 3 : 결혼식
 */
public class Main {

    static int ANSWER = Integer.MIN_VALUE;

    static int N;
    static ArrayList<Guest> information = new ArrayList<>();

    public void solution() {

        Collections.sort(information);

        int cnt = 0;
        for (Guest g : information) {


            if (g.getState() == 's') {
                cnt++;
            } else {
                cnt--;
            }

            ANSWER = Math.max(ANSWER, cnt);

        }

    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int s, e;
        for (int i = 0; i < N; i++) {
            s = sc.nextInt();
            e = sc.nextInt();
            
            information.add(new Guest(s, 's'));
            information.add(new Guest(e, 'e'));
            
        }

        m.solution();
        System.out.println(ANSWER);

    }
}