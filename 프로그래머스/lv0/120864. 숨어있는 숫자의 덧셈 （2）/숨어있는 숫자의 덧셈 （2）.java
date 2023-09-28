class Solution {
       public int solution(String my_string) {

        String[] numbers = my_string.split("[a-zA-Z]+");

        int answer = 0;
        for (String n : numbers) {
            if (!n.equals("")) {
                answer += Integer.parseInt(n);
            }
        }

        return answer;
    }

}