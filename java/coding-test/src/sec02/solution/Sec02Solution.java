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
    public int score(int rep, int[] arr) {

        final int CORRECT = 1, WRONG = 0, POINT = 1;
        int score = 0;

        int mul = 1;
        for (int i = 0;i < rep;i++) {

            if (arr[i] == CORRECT) {
                score +=  POINT * (mul++);
            } else {
                mul = 1;
            }

        }

        return score;

    }

    /**
     * section 2 - 8 : 등수 구하기
     * 알파벳만 비교하여 회문인지 판단
     * 대소문자도 구분 X
     */
    public int[] ranking(int rep, int[] arr) {

        int[] rank = new int[rep];

        for (int i = 0;i < rep;i++) {

            int cnt = 1;

            for(int j = 0;j < rep;j++) {
                if (arr[j] > arr[i]) {
                    cnt++;
                }
            }

            rank[i] = cnt;

        }

        return rank;
    }

    /**
     * section 2 - 9 : 격자판 최대합
     *
     */
    public int findMatrixMaxSum(int rep, int[][] arr) {

        int max = 0, colSum, rowSum, downLeftSum, upRightSum;

        downLeftSum = upRightSum = 0;
        for (int i = 0; i < rep;i++) {

            rowSum = colSum = 0;

            for (int j = 0; j < rep;j++) {

                rowSum += arr[i][j];
                colSum += arr[j][i];

                if (i == j) {
                    downLeftSum += arr[i][j];
                }

                if (i + j == (rep - 1)) {
                    upRightSum += arr[i][j];
                }

            }

            max = Math.max(max, rowSum);
            max = Math.max(max, colSum);

        }

        max = Math.max(max, downLeftSum);
        max = Math.max(max, upRightSum);

        return max;

    }

    /**
     * section 2 - 10 : 봉우리
     */
    public int findTop(int rep, int[][] arr) {

        int ans = 0;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < rep;i++) {

            for (int j = 0; j < rep;j++) {

                boolean flag = true;

                for (int k = 0; k < 4; k++) {

                    int x = i + dx[k];
                    int y = j + dy[k];

                    if ( x >= 0 && x < rep &&  y >= 0 && y < rep && arr[x][y] >= arr[i][j]) {
                        flag = false;
                        break;
                    }
                }

               if (flag) ans++;

            }

        }

        return ans;

    }

    /**
     * section 2 - 11 : 임시 반장 정하기
     */
    public int selectClassPresident(int rep, int[][] arr) {

        int max = Integer.MIN_VALUE, ans = 0, cnt;

        for (int i = 0; i < rep;i++) {

            cnt = 0;

            for (int j = 0; j < rep;j++) {

                for (int k = 0; k < rep; k++) {

                    if (arr[i][k] == arr[j][k]) {
                        cnt++;
                        break;
                    }
                }
            }

            if (max < cnt) {
                max = cnt;
                ans = i + 1;
            }

        }

        return ans;

    }

    /**
     * section 2 - 12 : 멘토링
     */
    public int mentor(int M, int N, int[][] arr) {

        int ans = 0;

        int cnt, ipos, jpos;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                cnt = 0;
                for (int k = 0; k < M; k++) {
                    ipos = jpos = 0;
                    for (int s = 0; s < N; s++) {

                        if (arr[k][s] == i) {   // i학생 등수
                            ipos = s;
                        }

                        if (arr[k][s] == j) {   // j학생 등수
                            jpos = s;
                        }

                    }

                    if (ipos < jpos) { // i 학생이 j 학생 등수보다 앞서면
                        cnt++;
                    }

                }

                if (cnt == M) { // i 학생이 j 학생보다 모든 회차에서 우수하면
                    ans++;
                }

            }

        }

        return ans;


    }

}
