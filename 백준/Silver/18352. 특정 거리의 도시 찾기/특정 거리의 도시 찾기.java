import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_특정거리의도시찾기
 * @author parkrootseok
 */

public class Main {

    static class City {

        int name;
        List<Integer> adjacentCities = new ArrayList<>();

        public City(int name) {
            this.name = name;
        }

    }


    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int cityNumber;
    static int roadNumber;
    static int limit;
    static int start;

    static City[] cities;
    static List<Integer> result;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        cityNumber = Integer.parseInt(inputs[0]);
        roadNumber = Integer.parseInt(inputs[1]);
        limit = Integer.parseInt(inputs[2]);
        start = Integer.parseInt(inputs[3]);

        cities = new City[cityNumber + 1];
        for (int city = 1; city <= cityNumber; city++) {
            cities[city] = new City(city);
        }

        for (int road = 0; road < roadNumber; road++) {

            inputs = br.readLine().trim().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);

            cities[from].adjacentCities.add(to);

        }

        result = new ArrayList<>();
        bfs();

        if (result.isEmpty()) {
            sb.append("-1");
        } else {
            Collections.sort(result);
            result.forEach(r -> sb.append(r).append("\n"));
        }
        bw.write(sb.toString());
        bw.close();

    }

    public static void bfs() {

        boolean[] isVisited = new boolean[cityNumber + 1];
        Queue<int[]> nodeQ = new ArrayDeque<>();
        nodeQ.add(new int[] {start, 0});
        isVisited[start] = true;

        while (!nodeQ.isEmpty()) {

            int[] node = nodeQ.poll();

            if (node[1] > limit) {
                continue;
            }

            if (node[1] == limit) {
                result.add(node[0]);
            }

            for (Integer adjacentCity : cities[node[0]].adjacentCities) {

                if (isVisited[adjacentCity]) {
                    continue;
                }

                isVisited[adjacentCity] = true;
                nodeQ.offer(new int[] {adjacentCity, node[1] + 1});

            }

        }

    }

}