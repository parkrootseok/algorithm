import java.util.*;

class Solution {
        public int solution(String A, String B) {
    
        if (A.equals(B)) {
            return 0;
        }
        
        Deque<Character> deque = new LinkedList<>();
        for (char c : A.toCharArray()) {
            deque.addLast(c);
        }

        int count = 1;
        while (B.length() > count) {

            deque.push(deque.removeLast());
            String shiftA = deque.toString()
                    .replaceAll("[^a-z]", "");

            if (shiftA.equals(B)) {
                return count;
            }

            count++;

        }
        
        return -1;

    }

}