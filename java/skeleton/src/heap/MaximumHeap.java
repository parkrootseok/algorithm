package heap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Maximum Heap(최대힙)
 * @author parkrootseok
 **/
class MaximumHeap {

    static class Heap {

        int[] datas;
        int lastIndex;

        Heap(int size) {
            lastIndex = 0;
            this.datas = new int[size];
        }

        void add(int data) {

            // 데이터 삽입
            datas[++lastIndex] = data;

            // 힙 정렬 진행
            up(lastIndex);

        }

        /**
         * 위로 올라가면서 힙 정렬
         */
        void up(int curIndex) {

            int parent = curIndex / 2;

            // 현재 인덱스가 루트 노드이면 종료
            if (parent < 1) {
                return;
            }

            // 큰 원소가 상위 노드에 있도록 현재 노드가 더 작다면 종료
            if (datas[parent] > datas[curIndex]) {
                return;
            }

            // 원소 교환
            swap(datas, parent, curIndex);

            // 교환 위치부터 다시 힙 정렬 시작
            up(parent);

        }

        /**
         * 힙에서 데이터 삭제
         */
        int remove() {

            // 데이터가 존재하지 않으면 종료
            if (isEmpty()) {
                return 0;
            }

            // 루트 노드의 값을 저장
            int root = datas[1];

            // 루트 노드에 가장 마지막에 위치한 데이터 삽입
            datas[1] = datas[lastIndex];

            // 루트 노드부터 다시 힙을 정렬
            down(1);
            lastIndex--;

            return root;
        }

        /**
         * 아래로 내려가면서 힙 정렬 진행
         */
        void down(int curIndex) {

            int lChild = curIndex * 2;
            int rChild = lChild + 1;

            // 자식 노드가 존재하지 않으면 종료
            if (lChild > lastIndex) {
                return;
            }

            // 좌, 우 자식 노드 중 데이터가 가장 큰 노드에 대한 인덱스를 검색
            int greater = lChild;
            if (rChild <= lastIndex) {

                // 우측 자식이 크다면 변경
                if (datas[greater] < datas[rChild]) {
                    greater = rChild;
                }
            }

            // 큰 원소가 상위 노드에 있도록 현재 노드가 더 크다면 종료
            if (datas[greater] < datas[curIndex]) {
                return;
            }

            // 가장 큰 노드와 현재 노드를 교환
            swap(datas, greater, curIndex);

            // 교환한 위치부터 다시 힙 정렬 진행
            down(greater);

        }

        void swap(int[] datas, int smaller, int curIndex) {

            int child = datas[smaller];
            datas[smaller] = datas[curIndex];
            datas[curIndex] = child;

        }

        boolean isEmpty() {

            // 데이터가 존재하지 않으면 종료
            if (lastIndex == 0) {
                return true;
            }

            return false;

        }

    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static String[] inputs;

    static int operatorNumber;
    static int operator;

    public static void main(String args[]) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        // 1. 연산의 개수 입력
        operatorNumber = Integer.parseInt(br.readLine().trim());

        // 2. 연산의 갯수만큼 연산 정보 입력(0이면 가장 작은 값 출력 후 제거, 아니라면 삽입)
        Heap maxHeap = new Heap(operatorNumber + 1);
        for (int curNumber = 0; curNumber < operatorNumber; curNumber++) {

            operator = Integer.parseInt(br.readLine().trim());

            if (operator == 0) {
                sb.append(maxHeap.remove()).append("\n");
            } else {
                maxHeap.add(operator);
            }

        }

        bw.write(sb.toString());
        bw.close();
        return;

    }

}