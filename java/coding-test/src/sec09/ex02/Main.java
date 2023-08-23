package sec09.ex02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Room implements Comparable<Room> {

    private int start;
    private int end;

    public Room(int height, int finish) {
        this.start = height;
        this.end = finish;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public int compareTo(Room o) {
        if (this.end == o.end) {
            return this.start - o.start;
        } else {
            return this.end - o.end;
        }
    }

}

/**
 * Section 9 - 2 : 회의실 배정
 */
public class Main {

    static int ANSWER = 0;

    static int N;
    static ArrayList<Room> information = new ArrayList<>();

    public void solution() {

        Collections.sort(information);

        int min = Integer.MIN_VALUE;
        for (Room r : information) {

            if (r.getStart() >= min) {
                min = r.getEnd();
                ANSWER++;
            }

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
            
            information.add(new Room(s, e));
            
        }

        m.solution();
        System.out.println(ANSWER);

    }
}