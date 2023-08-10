package sec06.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import sec05.solution.Person;

public class Sec06Solution {

    public Sec06Solution() {
    }

    /**
     * section 6 - 1 : 선택 정렬
     */
    public int[] selectionSort(int[] arr) {

        int tmp, idx;

        for (int i = 0 ; i < arr.length ; i++) {

            idx = i;

            for (int j = i + 1 ; j < arr.length ; j++) {
                if (arr[idx] > arr[j]) {
                    idx = j;
                }
            }

            tmp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = tmp;

        }

        return arr;

    }

    /**
     * section 6 - 2 : 버블 정렬
     */
    public int[] bubbleSort(int[] arr) {

        int tmp;

        for (int i = 0 ; i < arr.length - 1; i++) {

            for (int j = i ; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }


        }

        return arr;

    }

    /**
     * section 6 - 3 : 삽입 정렬
     */
    public int[] insertionSort(int[] arr) {

        int i, j, tmp;

        for (i = 1 ; i < arr.length; i++) {

            tmp = arr[i];

            for (j = i - 1; j >= 0; j--) {

                if (arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }

            }

            arr[j + 1] = tmp;
        }

        return arr;

    }

    /**
     * section 6 - 4 : Least Recently Used
     */
    public int[] LeastRecentlyUsed(int S, int[] arr) {

        int[] ans = new int[S];

        for (int x : arr) {

            int pos = -1;

            for (int i = 0 ; i < S ; i++) {

                if (x == ans[i]) pos = i;

            }

            if (pos == -1) {
                for (int i = S - 1; 0 < i; i--) {
                    ans[i] = ans[i - 1];
                }
                ans[0] = x;
            } else {
                for (int i = pos; 0 < i; i--) {
                    ans[i] = ans[i - 1];
                }
                ans[0] = x;
            }

        }

        return ans;

    }

    /**
     * section 6 - 5 : 중복 확인
     */
    public String checkDuplication(int[] arr) {

        String ans = "U";
        Arrays.sort(arr);

        for (int i = 0 ; i < arr.length - 1; i++) {

            if (arr[i] == arr[i + 1]) {
                return "D";
            }
        }

        return ans;

    }


}
