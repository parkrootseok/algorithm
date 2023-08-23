package sec09.ex01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Player implements Comparable<Player> {

    private int height;
    private int weight;

    public Player(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Player o) {
        return o.height - this.height;
    }
}

/**
 * Section 9 - 1 : 씨름선수
 */
public class Main {

    static int ANSWER = 0;

    static int N;
    static ArrayList<Player> information = new ArrayList<>();

    public void  solution() {

        Collections.sort(information);

        int max = Integer.MIN_VALUE;
        for (Player p : information) {

            if (p.getWeight() > max) {
                max = p.getWeight();
                ANSWER++;
            }

        }

    }

    public static void main(String[] args) {

        Main m = new Main();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int h, w;
        for (int i = 0; i < N; i++) {
            h = sc.nextInt();
            w = sc.nextInt();
            
            information.add(new Player(h, w));
            
        }

        m.solution();
        System.out.println(ANSWER);

    }
}