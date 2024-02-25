package heap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Minimum Heap(최소힙)
 * @author parkrootseok
 **/
class MinimumHeap {

    static class Heap {

        int[] elements;
        int lastIndex;

        Heap(int size) {
            lastIndex = 0;
            this.elements = new int[size];
        }

        /**
         * 원소 추가
         */
        void add(int element) {

            // 1. 마지막 인덱스를 증가시키고 해당 위치에 원소를 삽입
            elements[++lastIndex] = element;

            // 2. 원소 삽입 후 부모 노드에 자신보다 큰 값이 있다면 교체
            up(lastIndex);

        }

        /**
         * 부모 노드를 확인하여 자신보다 크다면 현재 위치와 교환
         */
        void up(int curIndex) {

            int parent = curIndex / 2;

            /**
             * 전처리 로직
             * 1. 현재 노드 위치 확인
             *  1-1. 루트 노드인 경우 종료
             * 2. 현재 원소 값을 비교
             *  2-1. 부모 노드보다 크다면 종료
             * 3. 부모 노드 원소와 현재 노드의 원소를 교체
             */
            // 1. 현재 노드 위치 확인
            if (parent < 1) {
                // 1-1. 루트 노드인 경우 종료
                return;
            }

            // 2. 현재 원소 값을 비교
            if (elements[parent] < elements[curIndex]) {
                // 2-1. 부모 노드보다 크다면 종료
                return;
            }

            // 3. 부모 노드 원소와 현재 노드의 원소를 교체
            swap(elements, parent, curIndex);

            /**
             * 재귀 호출
             * 1. 부모 노드부터 다시 탐색을 시작하도록 부모 노드를 파라미터로 하여 재귀 호출
             */
            up(parent);

        }

        /**
         * 원소 삭제
         */
        public int remove() {

            // 1. 원소가 없다면 종료
            if (isEmpty()) {
                return 0;
            }

            // 2. 삭제할 노드(루트 노드)의 값을 저장
            int root = elements[1];

            // 3. 루트 노드 값을 마지막에 위치하는 노드의 값으로 교체
            elements[1] = elements[lastIndex];

            // 4. 자신보다 더 작은 노드가 있는지 재귀를 이용해 탐색 시작
            down(1);

            // 5. 삭제한 노드에 대하여 사이즈 감소
            lastIndex--;

            // 6. 삭제한 노드의 원소 반환
            return root;

        }

        /**
         * 자식 노드를 확인하여 자신 보다 작은 원소를 가지는 노드가 있는지 확인 후 교환
         */
        void down(int curIndex) {

            int lChild = curIndex * 2;
            int rChild = lChild + 1;

            /**
             * 전처리 로직
             * 1. 좌측 자식 노드 유무 확인
             *  1-1. 존재하지 않으면 종료
             * 2. 좌, 우 자식 노드 중 더 작은 원소값 탐색
             *  2-1. 좌, 우측 자식의 값을 비교하여 더 작은값으로 저장
             * 3. 자식 중 가장 작은 원소와 부모 원소 비교
             *  3-1. 작다면 종료
             *  3-2. 크다면 교환
             */
            // 1. 좌측 자식 노드 유무 확인
            if (lChild > lastIndex) {
                //  1-1. 존재하지 않으면 종료
                return;
            }

            // 2. 좌, 우 자식 노드 중 더 작은 원소값 탐색
            int smaller = lChild;
            if (rChild <= lastIndex) {

                // 2-1. 좌, 우측 자식의 값을 비교하여 더 작은값으로 저장
                if (elements[smaller] > elements[rChild]) {
                    smaller = rChild;
                }

            }

            // 3. 자식 중 가장 작은 원소와 부모 원소 비교
            if (elements[curIndex] < elements[smaller]) {
                // 3-1. 작다면 종료
                return;
            }

            // 3-2. 크다면 교환
            swap(elements, smaller, curIndex);

            /**
             * 재귀 호출
             * 1. 교환된 위치를 파라미터로 하여 재귀 호출
             */
            // 1. 교환된 위치를 파라미터로 하여 재귀 호출
            down(smaller);

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
        Heap minHeap = new Heap(operatorNumber + 1);
        for (int curNumber = 0; curNumber < operatorNumber; curNumber++) {

            operator = Integer.parseInt(br.readLine().trim());

            if (operator == 0) {
                sb.append(minHeap.remove()).append("\n");
            } else {
                minHeap.add(operator);
            }

        }

        bw.write(sb.toString());
        bw.close();
        return;

    }

}