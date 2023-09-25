import java.util.ArrayList;

class Solution {
public int[] solution(String myString) {

        int cnt = 0;
        ArrayList<Integer> result = new ArrayList<>();
        for(char c : myString.toCharArray()) {
            if (c == 'x') {
                result.add(cnt);
                cnt = 0;
            } else {
                cnt++;
            }
        }
        result.add(cnt);

        return result.stream()
                .mapToInt(i -> i)
                .toArray();

    }
}