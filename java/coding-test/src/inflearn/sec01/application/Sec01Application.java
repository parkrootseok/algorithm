package inflearn.sec01.application;

import java.util.Scanner;
import inflearn.sec01.solution.Sec01Solution;

public class Sec01Application {

    public Sec01Application() {
    }

    private final Sec01Solution sec01Solution = new Sec01Solution();
    private final Scanner sc = new Scanner(System.in);

    public void ex01() {

        String str = sc.next();
        char target = sc.next().charAt(0);
        System.out.println(sec01Solution.findCharacterInString(str, target));

    }

    public void ex02() {

        String str = sc.next();
        System.out.println(sec01Solution.changeCase(str));

    }

    public void ex03() {
        String str = sc.nextLine();
        System.out.println(sec01Solution.findLongWord(str));
    }

    public void ex04() {

        int num = sc.nextInt();

        for (int i = 0; i < num ; i++) {
            String str = sc.next();
            System.out.println(sec01Solution.reverseWord(str));
        }

    }

    public void ex05() {

         String str = sc.next();
         System.out.println(sec01Solution.reverseSpecificWord(str));

    }

    public void ex06() {

        String str = sc.next();
        System.out.println(sec01Solution.removeDuplicationChar(str));

    }

    public void ex07() {
        String str = sc.next();
        System.out.println(sec01Solution.IsPalindrome(str));
    }

    public void ex08() {
        String str = sc.nextLine();
        System.out.println(sec01Solution.IsValidPalindrome(str));
    }

    public void ex09() {
        String str = sc.next();
        System.out.println(sec01Solution.extractDigit(str));
    }

    public void ex10() {

        String str = sc.next();
        char target = sc.next().charAt(0);

        for(int x : sec01Solution.findMinimumSDistance(str, target)) {
            System.out.print(x + " ");
        }

    }
    public void ex11() {

        String str = sc.next();
        System.out.println(sec01Solution.compressString(str));

    }

    public void ex12() {

        int number = sc.nextInt();
        String str = sc.next();
        System.out.println(sec01Solution.encrypt(number, str));

    }

}

