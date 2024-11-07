import java.io.*;
import java.util.Stack;

/**
 * BOJ_문자열폭발
 * @author parkrootseok
 */

public class Main {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static String origin;
    static String bomb;
    static StringBuilder result;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        origin = br.readLine().trim();
        bomb = br.readLine().trim();
        Stack<Character> characters = new Stack<>();

        for (int idx = 0; idx < origin.length(); idx++) {

            // 스택에 문자 삽입
            characters.push(origin.charAt(idx));

            // 문자의 길이가 폭탄 문자열보다 긴 경우
            if (bomb.length() <= characters.size()) {

                // 마지막에 삽입된 문자를 기준으로 비교
                boolean isPossible = true;
                for (int check = 0; check < bomb.length(); check++) {

                    if (characters.get(characters.size() - bomb.length() + check) != bomb.charAt(check)) {
                        isPossible = false;
                        break;
                    }

                }

                // 동일하다면 폭탄 문자열 길이만큼 추출
                if (isPossible) {
                    for (int rep = 0; rep < bomb.length(); rep++) {
                        characters.pop();
                    }
                }

            }

        }

        if (characters.isEmpty()) {
            sb.append("FRULA");
        } else {
            for (Character c : characters) {
                sb.append(c);
            }
        }

        bw.write(sb.toString());
        bw.close();

    }

}