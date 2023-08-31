package inflearn.sec04.solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

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

            if (map.get(A[left]) == 0) {
                map.remove(A[left]);
            } left++;

            result.add(map.size());

        }

        return result;

    }

    /**
     * section 4 - 4 : 모든 아나그램 찾기
     */
    public int findAnagram(String s, String t) {

        HashMap<Character, Integer> bMap = new HashMap<>();
        HashMap<Character, Integer> aMap = new HashMap<>();
        int ans = 0, length = t.length();

        for (char c : t.toCharArray()) {
            bMap.put(c, bMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0 ; i < length - 1 ; i++) {
            aMap.put(s.charAt(i), aMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        int lt = 0, rt;
        for (rt = length - 1; rt < s.length() ; rt++) {

            aMap.put(s.charAt(rt), aMap.getOrDefault(s.charAt(rt), 0) + 1);

            if (aMap.equals(bMap)) {
                ans++;
            }

            aMap.put(s.charAt(lt), aMap.get(s.charAt(lt)) - 1);
            if (aMap.get(s.charAt(lt)) == 0) {
                aMap.remove(s.charAt(lt));
            } lt++;


        }

        return ans;

    }

    /**
     * section 4 - 5 : K번째 큰 수
     */
    public int maxNumber(int[] A, int k) {

        int ans = 0;
        TreeSet<Integer> treeSet = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0 ; i < A.length ; i++) {
            for (int j = i + 1 ; j < A.length ; j++) {
                for (int l = j + 1 ; l < A.length ; l++) {
                    treeSet.add(A[i] + A[j] + A[l]);
                }
            }
        }

        int cnt = 0;
        for (int x : treeSet) {
           if (++cnt == k) {
               ans = x;
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
