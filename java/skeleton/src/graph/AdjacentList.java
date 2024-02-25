package graph;

import graph.EdgeList.Edge;
import java.util.ArrayList;
import java.util.List;

/**
 * Graph - Adjacent List
 * @author parkrootseok
 *
 * - 인접 리스트를 이용한 그래프 구현 (정점 중심)
 * 1. 정점들의 인접 정보를 리스트를 활용하여 구현
 */
public class AdjacentList {

    static class Vertex {

        int name;

        /**
         * 인접 리스트
         * - 인접 정점들에 대한 정보를 기록하기 위함
         *  -> 해당 리스트에 존재하는 정점의 번호와 이를 가지고 있는 정점은 인접 정점인
         */
        List<AdjacentVertex> adjacentVertices;

        Vertex(int name) {
            this.name = name;
            this.adjacentVertices = new ArrayList<>();
        }

    }

    static class AdjacentVertex {

        int to;
        int weight;

        AdjacentVertex(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

    }

    static class Graph {

        Vertex[] vertices;

        public Graph(int vertexNumber) {
            this.vertices = new Vertex[vertexNumber];
        }

    }

}
