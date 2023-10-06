import java.util.*;

class Solution {
      public String[] solution(String[] picture, int k) {

        List<String> scale = new ArrayList<>();
        for (String p : picture) {

            for (int i = 0 ; i < k ; i++) {

                StringBuilder sb = new StringBuilder();

                for (char c : p.toCharArray()) {
                    sb.append(String.valueOf(c).repeat(k));
                }

                scale.add(sb.toString());

            }

        }

        return scale.toArray(new String[]{});

    }
}