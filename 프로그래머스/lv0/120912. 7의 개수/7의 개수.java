import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] array) {

        String str = Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());

        return (int) str.chars()
                .filter(c -> c == '7')
                .count();
    }
}