package inflearn.sec03.solution;

import java.util.ArrayList;
import java.util.Arrays;

public class Sec03Solution {

    public Sec03Solution() {}

    /**
     * section 3 - 1 : 배열 합치기
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
     * section 3 - 2 : 공통원소 구하기
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
     * section 3 - 3 : 최대 매출
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
     * section 3 - 4 : 연속 부분수열
     */
    public int continuousSubsequence(int size, int target, int[] arr) {

        int left = 0, sum = 0, ans = 0;
        for (int right = 0;right < size;right++) {

            sum += arr[right];

            if (sum == target) {
                ans++;
            }

            while (sum >= target) {

                sum -= arr[left++];
                if (sum == target) {
                    ans++;
                }
            }

        }

        return ans;
    }

    /**
     * section 3 - 5 : 연속된 자연수의 합
     */
    public int sumContinuousNumber(int target) {

        int sum, ans, left = 1;
        sum = ans = 0;


//        /* 수학적 접근 */
//        int t = --target, cnt = 1;
//        while (t > 0) {
//            cnt++;
//            t -= cnt;
//            if (t % cnt == 0) {
//                ans++;
//            }
//        }

        for (int right = 1 ; right < (target / 2) + 1 ; right++) {

            sum += right;

            if (sum == target) {
                ans ++;
            }

            while (sum >= target) {

                sum -= left++;
                if (sum == target) {
                    ans++;
                }
            }

        }

        return ans;

    }

    /**
     * section 2 - 6 : 최대 길이 연속부분수열
     */
    public int maximumLengthContinuousSubsequence(int size, int rep, int[] arr) {

        int ans = 1, left, cnt, r;

        left = cnt = 0;
        for (int right = 0;right < size;right++) {

            if (arr[right] == 0) {
                cnt++;
            }

            while (cnt > rep) {
                if (arr[left] == 0) {
                    cnt --;
                }
                left++;
            }

            ans = Math.max(ans,  right - left + 1);

        }

        return ans;
    }

}
