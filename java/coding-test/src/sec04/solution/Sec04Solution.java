package sec04.solution;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Sec04Solution {

    public Sec04Solution() {
    }

    /**
     * section 4 - 1 : 학급 회장 오름차순 정렬된 두 배열을 합쳐서 다시 오름차순 정렬
     */
    public Character votingResult(int n, String sign) {

        Character ans = ' ';
        HashMap<Character, Integer> result = new HashMap<>();

        for (char s : sign.toCharArray()) {
            // getOrDefault : 찾는 값이 있다면 value 없다면 지정한 default 값을 반환
            result.put(s, result.getOrDefault(s, 0) + 1);
        }

        int max = Integer.MIN_VALUE;
        for (Character k : result.keySet()) {

            if (max < result.get(k)) {
                max = result.get(k);
                ans = k;
            }


        }

        return ans;

    }

    /**
     * section 4 - 2 : 아나그램
     */
    public String anagram(String a, String b) {

        String ans = "YES";

        HashMap<Character, Integer> aResult = new HashMap<>();

        for (char c : a.toCharArray()) {
            aResult.put(c, aResult.getOrDefault(c, 0) + 1);
        }

        for (Character c : b.toCharArray()) {

            if (!aResult.containsKey(c) || aResult.get(c) == 0) {
                return "NO";
            }

            aResult.put(c, aResult.get(c) - 1);

        }

        return ans;

    }

    /**
     * section 4 - 3 : 매출액 종류
     */
    public ArrayList<Integer> kindOfSale(int n, int K, int[] A) {

        int i;
        ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (i = 0 ; i < K - 1 ; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }

        /* Two-Pointers + Sliding Window */
        int left = 0;
        for (int right = K - 1 ; right < n; right++) {

            map.put(A[right], map.getOrDefault(A[right], 0) + 1);

            map.put(A[left], map.get(A[left]) - 1);

            if (map.get(A[left++]) == 0) {
                map.remove(A[left]);
            }

            result.add(map.size());

        }

        return result;

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
