class Solution {
      public int solution(int i, int j, int k) {

        int answer = 0;

        for (int start = i ; start <= j ; start++) {

            String s = String.valueOf(start);

            for (int pos = 0 ; pos < s.length() ; pos++) {
                if (s.charAt(pos) == k + '0') {
                    answer++;
                }
            }

        }

        return answer;

    }
}