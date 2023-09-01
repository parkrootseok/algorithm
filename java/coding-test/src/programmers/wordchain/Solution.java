package programmers.wordchain;

import java.util.HashSet;
import java.util.Scanner;

public class Solution {

    public int[] solution(int n, String[] words) {

        int[] answer = {0, 0};
        HashSet<String> wordLog = new HashSet<>();

        wordLog.add(words[0]);
        for (int i = 1 ; i < words.length ; i++) {

            char start = words[i].charAt(0);
            char end = words[i - 1].charAt(words[i - 1].length() - 1);

            wordLog.add(words[i]);

            if (start != end || wordLog.size() != i + 1) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }

        }

        return answer;

    }

    public static void main(String[] args) {

        Solution s = new Solution();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int T = sc.nextInt();
        String[] words = new String[T];
        for (int i = 0 ; i < T ; i++) {
            words[i] = sc.next();
        }

        int [] result = s.solution(n, words);
        System.out.printf("[%d, %d]", result[0], result[1]);

    }

}
