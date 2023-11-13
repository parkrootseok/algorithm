import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static int ANSWER;
    static String password;

    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {1, 0, 1, -1};

    public static void main(String args[]) throws Exception {

        String patternStr = "(00)|(11)|(22)|(33)|(44)|(55)|(66)|(77)|(88)|(99)";
        Pattern pattern = Pattern.compile(patternStr);

        for (int i = 1; i <= 10; i++) {

            bw.write("#" + i + " ");

            String[] inputs = br.readLine().split(" ");
            N = Integer.parseInt(inputs[0]);
            password = inputs[1];

            Matcher matcher = pattern.matcher(password);
            while (matcher.find()) {
                password = password.replaceAll(patternStr, "");
                matcher = pattern.matcher(password);
            }

            bw.write(password + "\n");

        }

        bw.close();

    }

}