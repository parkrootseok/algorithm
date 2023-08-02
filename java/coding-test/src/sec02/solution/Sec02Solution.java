package sec02.solution;

import java.util.ArrayList;

public class Sec02Solution {

    public Sec02Solution() {}

    /**
     * section 2 - 1 : 큰 수 찾기
     * 자신의 앞보다 큰 수 검색
     */
    public ArrayList<Integer> printBiggerThanFront(int size, int [] arr) {

        int idx = 0;
        ArrayList<Integer> ans = new ArrayList<>();

        ans.add(arr[0]);
        for (int i = 1; i < size;i++) {

            if(arr[i] > arr[i - 1]) {
               ans.add(arr[i]);
            }

        }

        return ans;

    }

    /**
     * section 2 - 2 : 보이는 학생
     * 볼 수 있는 최대 학생 수 출력
     */
    public int calculateVisibleStudent(int studentNumber, int[] height) {

        int max  = height[0], cnt = 1;

        for (int i = 1;i < studentNumber;i++) {

            if (height[i] > max) {
                max = height[i];
                cnt++;
            }

        }

        return cnt;

    }

    /**
     * section 2 - 3 : 가위바위보
     *
     */
    public char[] whoIsWinner(int rep, int[] A, int []B) {

        final int SCISSORS = 1, ROCK = 2, PAPER = 3;


        char[] winner = new char[rep];

        for (int i = 0; i < rep ; i++) {

            if (A[i] == B[i]) {
                winner[i] = 'D';
            } else if (A[i] == SCISSORS && B[i] == PAPER) {
                winner[i] = 'A';
            } else if (A[i] == ROCK && B[i] == SCISSORS) {
                winner[i] = 'A';
            } else if (A[i] == PAPER && B[i] == ROCK) {
                winner[i] = 'A';
            } else {
                winner[i] = 'B';
            }

        }

        return winner;

    }

    /**
     * section 2 - 4 : 피보나치 수열
     */
    public void fibonacciSequence(int n) {

        int[] ans = new int[n];

        for (int i = 0; i < n ; i++) {

            if (i == 0 || i == 1) {
                ans[i] = 1;
            } else {
                ans[i] = ans[i - 1] + ans[i - 2];
            }

            System.out.print(ans[i] + " ");
        }

    }

    /**
     * section 2 - 5 : 소수 숫자 세기
     * 1 ~ N 내에 있는 소수 숫자 세기
     */
    public int countDecimalNumber(int n) {

        int answer = 0;
        int[] arr = new int[n + 1];

        for (int i = 2;i <= n;i++) {

            if (arr[i] == 0) {
                answer++;
            }

            for (int j = i;j<=n;j=j+i) { // 1을 제외한 자신보다 작은 자연수의 곱이 아니면 소수
                arr[j] = 1;
            }

        }

        return answer;

    }

    /**
     * section 2 - 6 : 뒤집은 소수
     */
    public ArrayList<Integer> reverseNumberIsDecimal(int n, int[] arr) {

        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < n ; i++) {

            int tmp = arr[i], reverseN = 0;

            while (tmp > 0) {   // 자연수 뒤집기
                reverseN = (reverseN * 10) + (tmp % 10);
                tmp /= 10;
            }

            if (isPrime(reverseN)) {
                answer.add(reverseN);
            }
        }

        return answer;

    }

    private boolean isPrime(int n) {

        if (n == 1) {
            return false;
        }

        for (int i = 2;i < n;i++) {
            if ((n % i) == 0) {
                return false;
            }
        }

        return true;

    }

    /**
     * section 2 - 7 : 점수 계산
     */
    public void IsPalindrome(String str) {

    }

    /**
     * section 1 - 8 : 유효한 회문
     * 알파벳만 비교하여 회문인지 판단
     * 대소문자도 구분 X
     */
    public String IsValidPalindrome(String str) {

        return "YES";
    }

    /**
     * section 1 - 9 : 숫자만 추출
     */
    public Integer extractDigit(String str) {

        return 0;

    }

    /**
     * section 1 - 10 : 가장 짧은 문자거리
     */
    public int[] findMinimumSDistance(String str, char t) {

        return null;

    }

    /**
     * section 1 - 11 : 문자열 압축
     */
    public String compressString(String str) {

        String ans = "";
        return ans;

    }

    /**
     * section 1 - 12 : 암호
     */
    public String encrypt(int number, String str) {

        String ans = "";
        return ans;

    }

}
