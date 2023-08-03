package sec03.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sec03Solution {

    public Sec03Solution() {}

    /**
     * section 2 - 1 : 배열 합치기
     * 오름차순 정렬된 두 배열을 합쳐서 다시 오름차순 정렬
     */
    public ArrayList<Integer> mergeArray(int[] A, int[] B) {

        int aIdx, bIdx, aLen = A.length, bLen = B.length, len = Math.min(aLen, bLen);
        ArrayList<Integer> ans = new ArrayList<>();

        aIdx = bIdx = 0;
        while (aIdx < len && bIdx < len) {

            if (A[aIdx] < B[bIdx]) {
                ans.add(A[aIdx++]);
            } else {
                ans.add(B[bIdx++]);
            }

        }

       while (aIdx < aLen) {
           ans.add(A[aIdx++]);
       }

        while (bIdx < bLen) {
            ans.add(B[bIdx++]);
        }

       return ans;

    }

    /**
     * section 2 - 2 : 공통원소 구하기
     */
    public ArrayList<Integer> findCommonElement(int[] A, int[] B) {

        int aIdx = 0, bIdx = 0, aLen = A.length, bLen = B.length;
        ArrayList<Integer> ans = new ArrayList<>();

        // 먼저 두 배열을 오름차순 정렬 (오름차순의 특성을 이용하기 위함)
        Arrays.sort(A);
        Arrays.sort(B);

        // 앞에서 한 오름차순 정렬에 의해 모든 원소를 비교할 필요가 없음
        // 오름차순 정렬에 의해 원소값이 작지 않은 배열은 작은 배열보다 반드시 크거나 같은 배열 원소만 가지고 있기 때문에
        // 하나의 배열에서 원소값이 작을 때, 다른 배열에서 해당 원소와 동일한 원소를 가질 수 없음
        // 그러한 성질을 이용하여 모든 배열을 탐색하지 않고 하나의 배열만 수행하여 완료할 수 있음
        while (aIdx < aLen && bIdx < bLen) {

            if (A[aIdx] < B[bIdx]) {
                aIdx++;
            } else if (A[aIdx] > B[bIdx]) {
                bIdx++;
            } else {
                ans.add(A[aIdx]);
                aIdx++;
                bIdx++;
            }

        }

        return ans;
    }

    /**
     * section 2 - 3 : 최대 매출
     * 연속된 K일 동안 최대 매출 계산
     */
    public int calculateMaximumSales(int K, int[] A) {

        int ans, sum, max, days = A.length;

        // 맨 처음날부터 K까지 합 구하기
        sum = 0;
        for (int i = 0; i < K ; i++) {
            sum += A[i];
        }
        ans = sum;

        // 이제부터는 하나씩 옮겨 가면서 (i - K)일의 값은 빼고 i날의 값을 더하여 최대값 계산
        for (int i = K; i < days;i++) {

            sum += (A[i] - A[i - K]);
            ans = Math.max(ans, sum);

        }

        return ans;

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
