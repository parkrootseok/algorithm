class Solution {
   public int[] solution(int[] arr, int k) {

        boolean flag = false;
        
        if (k % 2 == 0) {
            flag = true;
        }
        
        for (int i = 0 ; i < arr.length ; i++) {
            if (flag) {
                arr[i] += k;
            } else {
                arr[i] *= k;
            }
        }

        return arr;
        
    }
}