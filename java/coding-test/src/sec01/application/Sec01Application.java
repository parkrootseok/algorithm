package sec01.application;

import java.util.Scanner;
import sec01.solution.Solution;

public class Sec01Application {

    public Sec01Application() {
    }

    private final Solution solution = new Solution();
    private final Scanner sc = new Scanner(System.in);

    public void ex01() {

        String str = sc.next();
        char target = sc.next().charAt(0);
        System.out.println(solution.findCharacterInString(str, target));

    }

    public void ex02() {

        String str = sc.next();
        System.out.println(solution.changeCase(str));

    }

    public void ex03() {
        String str = sc.nextLine();
        System.out.println(solution.findLongWord(str));
    }

    public void ex04() {

        int num = sc.nextInt();

        for (int i = 0; i < num ; i++) {
            String str = sc.next();
            System.out.println(solution.reverseWord(str));
        }

    }

    public void ex05() {

         String str = sc.next();
         System.out.println(solution.reverseSpecificWord(str));

    }

    public void ex06() {

        String str = sc.next();
        System.out.println(solution.removeDuplicationChar(str));

    }

    public void ex07() {
        String str = sc.next();
        System.out.println(solution.IsPalindrome(str));
    }

    public void ex08() {
        String str = sc.nextLine();
        System.out.println(solution.IsValidPalindrome(str));
    }

    public void ex09() {
        String str = sc.next();
        System.out.println(solution.extractDigit(str));
    }

    public void ex10() {

        String str = sc.next();
        char target = sc.next().charAt(0);

        for(int x : solution.findMinimumSDistance(str, target)) {
            System.out.print(x + " ");
        }

    }
    public void ex11() {

        String str = sc.next();
        System.out.println(solution.compressString(str));

    }

    public void ex12() {

        int number = sc.nextInt();
        String str = sc.next();
        System.out.println(solution.encrypt(number, str));

    }

}

