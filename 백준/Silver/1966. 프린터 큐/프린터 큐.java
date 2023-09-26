import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 프린터 큐
 */

public class Main {
    static class Doc implements Comparable<Doc> {

       int num;
       int rank;

       public Doc(int num, int rank) {
           this.num = num;
           this.rank = rank;
       }

       @Override
       public int compareTo(Doc o) {
           return o.rank - this.rank;
       }

   }

    public static void main(String[] args) throws IOException {

        Main m = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int rep = Integer.parseInt(br.readLine());


        StringTokenizer st;
        for (int i = 0 ; i < rep ; i++) {

            Queue<Doc> docs = new LinkedList<>();
            int cnt = 0;

            st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());   // 문서 개수
            int M = Integer.parseInt(st.nextToken());   // 찾으려 하는 문서의 현재 순번

            st = new StringTokenizer(br.readLine(), " ");
            int j = 0;
            while (st.hasMoreTokens()){
                docs.offer(new Doc(j++, Integer.parseInt(st.nextToken())));
            }

            while (!docs.isEmpty()) {

                Doc cur = docs.poll();
                boolean isWork = true;

                for (Doc d : docs) {
                    if (d.rank > cur.rank) {    // 중요도가 더 높은 문서가 존재하면
                        isWork = false; // 수행 불가능
                        break;
                    }
                }

                if (isWork) {   // 수행 가능하면
                    cnt++;  // 카운트
                    if (cur.num == M) break;    // 이때 내가 찾으려 하던 큐의 순번이면 종료
                } else {        // 수행 불가능하면
                    docs.add(cur);  // 큐에 다시 삽입
                }
            }

            bw.write(cnt + "\n");

        }

        bw.close();

    }

}