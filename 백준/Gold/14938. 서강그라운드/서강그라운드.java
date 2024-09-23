import java.io.*;
import java.util.*;

/**
 * BOJ_서강그라운드
 * @author parkrootseok
 */
public class Main {

    static class Area implements Comparable<Area> {

        int name;
        int quantity;
        int min;
        List<AdjArea> adjAreas = new ArrayList<>();

        public Area(int name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        @Override
        public int compareTo(Area a) {
            return Integer.compare(this.min, a.min);
        }

    }

    static class AdjArea {

        int to;
        int distance;

        public AdjArea(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

    }

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static int areaCount;
    public static int range;
    public static int pathCount;
    public static Area[] areas;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        String[] inputs = br.readLine().trim().split(" ");
        areaCount = Integer.parseInt(inputs[0]);
        range = Integer.parseInt(inputs[1]);
        pathCount = Integer.parseInt(inputs[2]);

        int max = Integer.MIN_VALUE;
        areas = new Area[areaCount + 1];
        inputs = br.readLine().trim().split(" ");
        for (int count = 1; count <= areaCount; count++) {
            areas[count] = new Area(count, Integer.parseInt(inputs[count - 1]));
            max = Math.max(max, areas[count].quantity);
        }

        for (int count = 1; count <= pathCount; count++) {

            inputs = br.readLine().trim().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            int distance = Integer.parseInt(inputs[2]);

            areas[from].adjAreas.add(new AdjArea(to, distance));
            areas[to].adjAreas.add(new AdjArea(from, distance));

        }

        for (int count = 1; count <= areaCount; count++) {
            max = Math.max(max, dijkstra(count));
        }

        sb.append(max);
        bw.write(sb.toString());
        bw.close();

    }

    public static int dijkstra(int start) {

        for (int count = 1; count <= areaCount; count++) {
            areas[count].min = Integer.MAX_VALUE;
        }

        PriorityQueue<Area> queue = new PriorityQueue<>();
        areas[start].min = 0;
        queue.add(areas[start]);

        int quantity = 0;
        boolean[] isVisited = new boolean[areaCount + 1];
        while (!queue.isEmpty()) {

            Area area = queue.poll();

            if (isVisited[area.name]) {
                continue;
            }

            isVisited[area.name] = true;
            quantity += area.quantity;

            for (AdjArea adjA : area.adjAreas) {

                int distance = area.min + adjA.distance;

                if (range >= distance && areas[adjA.to].min > distance) {
                    areas[adjA.to].min = distance;
                    queue.add(areas[adjA.to]);
                }

            }

        }

        return quantity;

    }

}