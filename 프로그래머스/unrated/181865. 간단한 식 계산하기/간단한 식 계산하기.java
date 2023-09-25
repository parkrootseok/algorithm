class Solution {
    public int solution(String myString) {
        
        String[] strings = myString.split(" ");
        int a = Integer.parseInt(strings[0]), b = Integer.parseInt(strings[2]);
        String op = strings[1];

        if (op.equals("+")) {
            return a + b;
        } else if (op.equals("-")) {
            return a - b;
        } else {
            return a * b;
        }
        
    }

}