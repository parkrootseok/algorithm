import java.util.*;

class Solution {
      public String[] solution(String myString) {
        String[] strings = myString.split("x");
        ArrayList<String> result = new ArrayList<>();

        for (String str : strings) {

            if (!str.isEmpty()) {
                result.add(str);
            }

        }

        Collections.sort(result);

        return result.toArray(new String[0]);

    }
}