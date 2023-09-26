class Solution {
public String[] solution(String my_str, int n) {

        StringBuilder sb = new StringBuilder(my_str);
        int size = my_str.length();
        for (int i = 1 ; i <= my_str.length() / n ; i++) {

            sb.insert(n * i +  (i - 1)," ");

        }

        return sb.toString().split(" ");

    }
}