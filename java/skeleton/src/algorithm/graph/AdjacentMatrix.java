package algorithm.graph;

/**
 * Graph - Adjacent Matrix
 * @author parkrootseok
 *
 * - 인접 행렬을 이용한 그래프 구현 (정점 중심)
 * 1. 정점들의 인접 정보를 행렬을 활용하여 구현
 */
public class AdjacentMatrix {

    static class Vertex {

        int name;

    }

    static class Graph {

        Vertex[] vertices;

        /**
         * 인접 행렬
         * - 각 정점들의 인접 정보를 기록
         *  -> adjacentMatrix[to][from] > 0 : to, from 정점이 인접함
         *  -> adjacentMatrix[to][from] == 0  : to, from 정점이 인접하지 않음
         */
        int[][] adjacentMatrix;

        public Graph(int vertexNumber) {
            this.vertices = new Vertex[vertexNumber];
            this.adjacentMatrix = new int[vertexNumber][vertexNumber];
        }

    }

}
