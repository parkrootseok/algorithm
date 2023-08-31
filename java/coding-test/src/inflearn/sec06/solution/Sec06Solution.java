package inflearn.sec06.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    /**
     * section 6 - 6 : 장난꾸러기
     */
    public ArrayList<Integer> mischief(int[] arr) {

        ArrayList<Integer> ans  = new ArrayList<>();
        int[] tmp = arr.clone();
        Arrays.sort(tmp);

        for (int i = 0 ; i < arr.length ; i++) {

            if (tmp[i] != arr[i]) {
                ans.add(i + 1);
            }
        }

        return ans;

    }

    /**
     * section 6 - 7 : 좌표 정렬
     */
    public ArrayList<Coordinate> sortCoordinate(ArrayList<Coordinate> coordinates) {

        // Collections 클래스의 sort 메소드는 Comparable 클래스의 compareTo 메소드를 사용하여 정렬
        Collections.sort(coordinates);
        return coordinates;

    }

    /**
     * section 6 - 8 : 이분검색
     */
    public int binarySearch(int[] arr, int M) {

        int ans = 0;
        Arrays.sort(arr);

        int lt = 0, rt = arr.length - 1;

        while (lt <= rt) {

            int mid = (lt + rt) / 2;

            if (arr[mid] == M) {
                ans = mid + 1;
                break;
            } else if (arr[mid] < M) {
                lt = mid + 1;
            } else {
                rt =  mid - 1;
            }

        }


        return ans;
    }

    /**
     * section 6 - 9 : 뮤직비디오
     */
    public int musicVideo(int[] arr, int M) {

        int ans = 0;

        int lt = Arrays.stream(arr).max().getAsInt();
        int rt = Arrays.stream(arr).sum();

        while (lt <= rt) {

            int mid = (lt + rt) / 2;

            if (count(arr, mid) <= M) {
                ans = mid;
                rt = mid - 1;
            } else {
                lt = mid + 1;
            }

        }

        return ans;

    }

    private int count(int[] arr, int mid) {

        int cnt = 1, sum = 0;
        for (int x : arr) {

            if (sum + x > mid) {
                cnt++;
                sum = x;
            } else {
                sum += x;
            }

        }

        return cnt;

    }

    /**
     * section 6 - 10 : 마구간 정하기
     */
    public int selectStall(int[] arr, int M) {

        int ans = 0;

        Arrays.sort(arr);
        int lt = 1;
        int rt = arr[arr.length - 1];

        while (lt <= rt) {

            int mid = (lt + rt) / 2;

            if (minDistinctCount(arr, mid) >= M) {  // M개 보다 많으면 간격이 더 넓어야 하고
                ans = mid;
                lt = mid + 1;
            } else {    // M개 보다 작으면 너무 긴격을 줄여야 함
                rt = mid - 1;
            }

        }

        return ans;

    }

    private int minDistinctCount(int[] arr, int mid) {

        int cnt = 1, sum = 0, ep = 0;

        for (int i = 0; i < arr.length ; i++) {

            if (arr[i] - arr[ep] >= mid) {
                cnt++;
                ep = i;
            }

        }

        return cnt;

    }

}
