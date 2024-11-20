import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BOJ_ACMCraft
 * @author parkrootseok
 */

public class Main {

    static class Vertex {

        int number;
        int buildTime;
        int inDegree = 0;
        List<Integer> adjVertices = new ArrayList<>();

        public Vertex(int number, int buildTime) {
            this.number = number;
            this.buildTime = buildTime;
        }

    }

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static int testCount;
    static int vertexCount;
    static int edgeCount;
    static int goal;

    static Vertex[] vertices;
    static boolean[] isVisited;
    static int[] minTime;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        testCount = Integer.parseInt(br.readLine().trim());
        for (int tCount = 0; tCount < testCount; tCount++) {

            String[] inputs = br.readLine().trim().split(" ");
            vertexCount = Integer.parseInt(inputs[0]);
            edgeCount = Integer.parseInt(inputs[1]);

            inputs = br.readLine().trim().split(" ");
            vertices = new Vertex[vertexCount + 1];
            for (int vCount = 1; vCount <= vertexCount; vCount++) {
                vertices[vCount] = new Vertex(vCount, Integer.parseInt(inputs[vCount - 1]));
            }

            for (int eCount = 0; eCount < edgeCount; eCount++) {

                inputs = br.readLine().trim().split(" ");
                int org = Integer.parseInt(inputs[0]);
                int dest = Integer.parseInt(inputs[1]);

                vertices[org].adjVertices.add(dest);
                vertices[dest].inDegree++;

            }

            goal = Integer.parseInt(br.readLine().trim());

            minTime = new int[vertexCount + 1];
            topologySort();
            sb.append(minTime[goal]).append("\n");

        }

        bw.write(sb.toString());
        bw.close();

    }

    public static void topologySort() {

        Queue<Integer> nodeQ = new ArrayDeque<>();

        // 위상 정렬을 수행하기 위해 이미 건설 가능한 건물들을 찾아 큐에 삽입
        for (int vCount = 1; vCount <= vertexCount; vCount++) {

            minTime[vCount] = vertices[vCount].buildTime;

            if (vertices[vCount].inDegree == 0) {
                nodeQ.offer(vCount);
            }

        }

        while (!nodeQ.isEmpty()) {

            // 건설 가능한 건물을 추출
            int cNode = nodeQ.poll();

            // 해당 건물이 선수 조건인 건물들을 탐색
            for (int nNode : vertices[cNode].adjVertices) {

                // 다음 건물에 대하여 현재 건물을 완료한 시간을 더하여 소요 시간을 초기화
                minTime[nNode] = Math.max(minTime[nNode], minTime[cNode] + vertices[nNode].buildTime);

                // 다음 건물에 대한 선수 조건 완료 체크
                vertices[nNode].inDegree--;

                // 모든 선수 조건을 만족했을 때 큐에 삽입
                if (vertices[nNode].inDegree == 0) {
                    nodeQ.offer(nNode);
                }

            }

        }

    }

}