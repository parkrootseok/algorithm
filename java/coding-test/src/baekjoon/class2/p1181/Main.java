package baekjoon.class2.p1181;

/**
 * 단어 정렬
 */

import com.sun.source.tree.Tree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static class Info implements Comparable<Info> {

        String str;
        int len;

        public Info(String str, int len) {
            this.str = str;
            this.len = len;
        }


        @Override
        public int compareTo(Info o) {
            if (this.len == o.len) {
                return this.str.compareTo(o.str);
            } else {
                return this.len - o.len;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(br.readLine());
        TreeSet<Info> infos = new TreeSet<>();
        for (int i = 0 ; i < n ; i++) {
            String str = br.readLine();
            infos.add(new Info(str, str.length()));

        }

        for (Info info : infos) {
            bw.write(info.str + "\n");
        }

        bw.close();

    }

}