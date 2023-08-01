package sec01.solution;

import java.util.ArrayList;

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

    /**
     * section 1 - 7 : 회문 문자열
     */
    public String IsPalindrome(String str) {

        /* 대소문자를 구별하지 않을 때 */
        String reverseStr = new StringBuilder(str).reverse().toString();

        if (!reverseStr.equals(str)) {
            return "NO";
        }

//        /* 대소문자 구별할 때 */
//        int left = 0, right = str.length() - 1;
//        while (left < right) {
//
//            if(str.charAt(left) != str.charAt(right)) {
//                return "NO";
//            }
//
//            left++;
//            right--;
//        }

//        int len = str.length();
//        for (int i = 0;i < len / 2; i++) {
//
//            if(str.charAt(i) != str.charAt(len - i - 1)) {
//                return "NO";
//            }
//
//        }

        return "YES";

    }

    /**
     * section 1 - 8 : 유효한 회문
     * 알파벳만 비교하여 회문인지 판단
     * 대소문자도 구분 X
     */
    public String IsValidPalindrome(String str) {

        str = str.toUpperCase().replaceAll("[^A-Z]", "");
        String convertStr = new StringBuilder(str).reverse().toString();

        if (!str.equals(convertStr)) {
            return "NO";
        }


//        int left = 0, right = str.length() - 1;
//        while (left < right) {
//
//            while (!Character.isAlphabetic(str.charAt(left))) {
//                left++;
//            }
//
//            while (!Character.isAlphabetic(str.charAt(right))) {
//                right--;
//            }
//
//            if(str.charAt(left) != str.charAt(right)) {
//                return "NO";
//            }
//
//            left++;
//            right--;
//
//        }

        return "YES";
    }

    /**
     * section 1 - 9 : 숫자만 추출
     */
    public Integer extractDigit(String str) {

        String ans = "";
        for (char x:str.toCharArray()) {

            if(Character.isDigit(x)) {
                ans += x;
            }

        }

        // parseInt : 원시 타입, valueOf :객체 타입
        return Integer.valueOf(ans);

    }

    /**
     * section 1 - 10 : 가장 짧은 문자거리
     */
    public int[] findMinimumSDistance(String str, char t) {

        int pos = 101, len = str.length();
        int[] ans = new int[str.length()];

        for (int i = 0; i < len; i++) {

            if (str.charAt(i) == t) {
                pos = 0;
                ans[i] = pos;
            } else {
                pos++;
                ans[i] = pos;
            }

        }

        for (int i = len - 1; 0 <= i; i--) {

            if (str.charAt(i) == t) {
                pos = 0;
            } else {
                pos++;
                ans[i] = Math.min(pos, ans[i]) ;
            }

        }

        return ans;

    }

    /**
     * section 1 - 11 : 문자열 압축
     */
    public String compressString(String str) {

        int i, cnt;
        str = str + " ";
        String ans = "";

        cnt = 1;
        for (i = 0;i < str.length() - 1;i++) {

            if (str.charAt(i) == str.charAt(i + 1)) {
               cnt++;
            } else {
                ans += str.charAt(i);
                if (cnt > 1) {
                    ans += cnt;
                    cnt = 1;
                }
            }
        }

        return ans;

    }

    /**
     * section 1 - 12 : 암호
     */
    public String encrypt(int number, String str) {

        String ans = "";
        int dis = str.length() / number;

        for (int i = 0; i < str.length(); i += dis) {

            String binaryNumber = str
                    .substring(i, i + dis)
                    .replace("#", "1")
                    .replace("*", "0");

            ans += (char)(Integer.parseInt(binaryNumber, 2));

        }

        return ans;

    }

}
