import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M, X, Y, K, ANSWER;
    static int CODE_LENGTH = 56;
    static String[] code;
    static Map<String, String> CODEBOOK = new HashMap<>();


    public static String filtering() {

        int codePosition = 0;
        for (int i = 0 ; i < code.length ; i++) {
            if (code[i].contains("1")) {
                codePosition = i;
                break;
            }
        }

        int end = code[codePosition].lastIndexOf("1") ;
        return code[codePosition].substring(end + 1 - CODE_LENGTH, end + 1);

    }

    public static int decode(String code) {

        String[] codes = new String[code.length() / 7];
        
        int k = 0;
        for (int i = 0 ; i < code.length() ; i += 7) {
            codes[k++] = code.substring(i, i + 7);
        }

        StringBuilder decodedCode = new StringBuilder();
        for (String c : codes) {
            decodedCode.append(CODEBOOK.get(c));
        }

        int even = 0, odd = 0;
        for (int i = 0 ; i < decodedCode.length(); i++) {
            int digit = i + 1;
            int number = Character.getNumericValue(decodedCode.charAt(i));

            if (digit % 2 == 0) {
                even += number;
            } else {
                odd += number;
            }

            ANSWER += number;

        }

        return (odd * 3) + even;

    }

    public static boolean isRight(int code) {

        if (code % 10 == 0) {
            return  true;
        }

        return false;

    }

    public static void main(String args[]) throws Exception {

        CODEBOOK.put("0001101", "0");
        CODEBOOK.put("0011001", "1");
        CODEBOOK.put("0010011", "2");
        CODEBOOK.put("0111101", "3");
        CODEBOOK.put("0100011", "4");
        CODEBOOK.put("0110001", "5");
        CODEBOOK.put("0101111", "6");
        CODEBOOK.put("0111011", "7");
        CODEBOOK.put("0110111", "8");
        CODEBOOK.put("0001011", "9");

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {

            bw.write("#" + i);

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            M = Integer.parseInt(inputs[1]);

            code = new String[N];
            for (int j = 0 ; j < N; j++) {
                code[j] = br.readLine();
            }

            ANSWER = 0;
            String filteredCode = filtering();
            if (!isRight(decode(filteredCode))) {
                ANSWER = 0;
            }

            bw.write(" " + ANSWER + "\n");

        }

        bw.close();

    }

}