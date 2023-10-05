import java.util.*;

class Solution {
      public int solution(String[] babbling) {

        String[] possible = new String[]{"aya", "ye", "woo", "ma"};
        int cnt = 0;
        for (String b : babbling) {

            for (String p : possible) {
                b = b.replace(p, " ");
            }

            if (b.replace(" ", "").length() == 0) {
                cnt++;
            }

        }

        return cnt;

    }
}