import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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
    static int[] distance;

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

        List<Integer> result = new ArrayList<>();
        dijkstra();

        for (int city = 1; city <= cityNumber; city++) {

            if (distance[city] == limit) {
                result.add(city);
            }

        }

        if (result.isEmpty()) {
            sb.append("-1");
        } else {
            for (Integer r : result) {
                sb.append(r).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();

    }

    public static void dijkstra() {

        distance = new int[cityNumber + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        boolean[] isVisited = new boolean[cityNumber + 1];
        Queue<City> cityQ = new ArrayDeque<>();
        cityQ.add(cities[start]);
        distance[start] = 0;

        while (!cityQ.isEmpty()) {

            City cur = cityQ.poll();

            if (isVisited[cur.name]) {
                continue;
            }

            isVisited[cur.name] = true;

            for (Integer adjacentCity : cur.adjacentCities) {

                City next = cities[adjacentCity];

                if (!isVisited[next.name] && distance[adjacentCity] > distance[cur.name] + 1) {
                    distance[adjacentCity] = distance[cur.name] + 1;
                    cityQ.offer(next);
                }

            }

        }

    }

}