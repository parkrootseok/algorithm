import java.util.*;

class Solution {
    public int solution(String[] babbling) {

        return (int) Arrays.stream(babbling)
                .filter(b -> b.matches("^(aya(?!aya)|ye(?!ye)|woo(?!woo)|ma(?!ma))+$"))
                .count();

    }

}