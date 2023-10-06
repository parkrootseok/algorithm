class Solution {
    public static final int AMERICANO = 4500;
    public static final int CAFE_LATTE = 5000;

    public int solution(String[] order) {

        int sum = 0;

        for (String o : order) {
            if(o.matches("^(ice|hot)*(americano|anything)(ice|hot)*$")) {
                sum += AMERICANO;
            } else {
                sum += CAFE_LATTE;
            }
        }

        return sum;

    }
}