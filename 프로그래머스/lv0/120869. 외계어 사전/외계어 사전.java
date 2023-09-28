class Solution {
 public int solution(String[] spell, String[] dic) {

        int answer = 2;
        for (String d : dic) {

            int cnt = 0;

            for (String s : spell) {

                if (d.contains(s)) {
                    cnt++;
                }

            }

            if (cnt == spell.length) {
                return 1;
            }
            
        }

        return answer;
    }

}