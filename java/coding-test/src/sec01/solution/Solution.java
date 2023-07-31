package sec01.solution;

public class Solution {

    public Solution() {}

    /**
     * section 1 - 1 : 문자 찾기
     * 한 개의 문자열과 특정 문자를 입력 받아
     * 입력 받은 문자의 갯수를 반환
     */
    public int findCharacterInString(String str, char target) {

        int answer = 0;

        str = str.toUpperCase();
        target = Character.toUpperCase(target);

        /* for문 */
//        for (int i = 0 ; i < str.length() ; i++) {
//            if (str.charAt(i) == target)
//                answer++;
//        }

        /* 향상된 for문 */
        for (char t: str.toCharArray()) {
            if(t == target) {
                answer++;
            }
        }

        return answer;

    }

    /**
     * section 1 - 2 : 대소문자 변환
     * 대문자는 소문자로 소문자는 대문자로 변환
     */
    public String changeCase(String str) {

        String answer = "";

        for (char x : str.toCharArray()) {
            if (Character.isLowerCase(x))
                answer += Character.toUpperCase(x);
            else
                answer += Character.toLowerCase(x);
        }

        return answer;

    }

    /**
     * section 1 - 3 : 가장 긴 단어 찾기
     */
    public String findLongWord(String str) {

        String answer = "";
        int max = Integer.MIN_VALUE, pos;


        /* split 활용 */
        String [] splitStr = str.split(" ");

        for (String s : splitStr) {
            if (answer.length() < s.length()) {
                answer = s;
            }
        }

//        /* substring, indexOf 활용 */
//        while ((pos = str.indexOf(" ")) != -1) {
//
//            String tmp = str.substring(0, pos);
//            int len = tmp.length();
//
//            if (len > max) {
//                max = len;
//                answer = tmp;
//            }
//
//            str = str.substring(pos + 1);
//
//        }
//
//        if (str.length() > max) {
//            answer = str;
//        }

        return answer;

    }

    /**
     * section 1 - 4 : 단어 뒤집기
     */
    public String reverseWord(String str) {

        String answer = "";

//        /* 인덱스 활용 - 1 */
//        for (int i = str.length() - 1; 0 <= i; i--) {
//            answer += str.charAt(i);
//        }


//        /* 인덱스 활용 - 2 */
//        char [] x = str.toCharArray();
//        int first = 0, last = str.length() - 1;
//
//        while (first < last) {
//            char tmp = x[first];
//            x[first] = x[last];
//            x[last] = tmp;
//
//            first++;
//            last--;
//        }
//
//        answer = String.valueOf(x);
//
        /* StringBuilder 활용 */
        StringBuilder sb = new StringBuilder(str);
        answer = sb.reverse().toString();

        return answer;

    }

    /**
     * section 1 - 5 : 특정 단어 뒤집기
     */
    public String reverseSpecificWord(String str) {

        String answer;
        char [] strToChar = str.toCharArray();
        int first = 0, last = str.length() - 1;

        while (first < last) {

            if (!Character.isAlphabetic(strToChar[first])) {
                first++;
            } else if (!Character.isAlphabetic(strToChar[last]))  {
                last--;
            } else {
                char tmp = strToChar[first];
                strToChar[first] = strToChar[last];
                strToChar[last] = tmp;
                first++;
                last--;
            }

        }
        answer = String.valueOf(strToChar);

        return answer;

    }

    /**
     * section 1 - 6 : 중복 문자 삭제
     */
    public String removeDuplicationChar(String str) {

        String answer = "";

        /* indexOf 활용 */
        for (int i = 0; i < str.length();i++) {

            if (str.indexOf(str.charAt(i)) == i) {
                answer += str.charAt(i);
            }
        }

        return answer;

    }

}
