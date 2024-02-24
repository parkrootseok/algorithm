package stack;

/**
 * Stack
 * @author parkrootseok
 **/
public class Stack {

    /**
     * Node 정의
     */
    static class Node<T> {

        T data;     // 노드에 저장된 데이터
        Node<T> next;  // 다음 노드를 가리키는 노드

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

    }

    /**
     * Stack 구현체 정의
     */
    static class StackImpl<T> {

        Node<T> head; // 가장 맨 처음 노드를 가리키는 노드
        int size; // 현재 연결리스트 원소 개수

        StackImpl() {
            this.head = new Node<T>(null, null);
            this.size = 0;
        }

        /**
         * 데이터 삽입
         * @param data -> 삽입할 데이터
         */
        void push(T data) {

            Node<T> newNode;

            // 새로운 노드를 할당한 후 현재 맨 앞에 있는 노드를 가리키게 하고
            newNode = new Node<T>(data, head.getNext());

            // 맨 앞 노드를 교체
            head.setNext(newNode);

            // 사이즈 증가
            size++;

        }

        /**
         * 데이터 삭제 (항상 첫 노드를 삭제)
         */
        T pop(T data) {

            // 삭제할 원소가 없다면 종료
            if (isEmpty()) {
                System.out.println("원소가 존재하지 않습니다.");
                return null;
            }

            Node<T> removeNode;

            // 삭제할 노드를 가져온다
            removeNode = head.getNext();

            // head가 가리키는 노드를 삭제할 노드의 다음 노드로 변경
            head.setNext(removeNode.getNext());

            // 사이즈 감소
            size--;

            // 삭제할 노드의 데이터를 반환한 후 종료
            return removeNode.data;

        }

        /**
         * 첫 데이터 반환
         */
        T peek() {

            // 삭제할 원소가 없다면 종료
            if (isEmpty()) {
                System.out.println("원소가 존재하지 않습니다.");
                return null;
            }

            Node<T> firstNode = head.getNext();
            return firstNode.data;

        }

        /**
         * 원소 존재 여부
         */
        boolean isEmpty() {

            if (size == 0) {
                return true;
            }

            return false;
        }

        /**
         * 현재 원소의 개수 반환
         */
        int size() {
            return this.size;
        }

        /**
         * 스택 원소 출력
         */
        void print() {

            if (isEmpty()) {
                System.out.println("원소가 존재하지 않습니다.");
                return;
            }

            Node cur = head.getNext();

            // 노드 탐색
            while (cur != null) {
                System.out.printf("[DATA : %d]\n", cur.data);
                cur = cur.getNext();
            }

        }

    }

    public static void main(String[] args) {

        StackImpl<Integer> stack = new StackImpl();

        System.out.println("원소 삽입");
        for (int index = 1; index <= 4; index++) {
            stack.push(index);
            System.out.printf("[삽입한 데이터 : %d]\n", index);
        }

        System.out.println("\n원소 출력");
        stack.print();

        System.out.println("\n원소 삭제");
        for (int index = 1; index <= 4; index++) {
            System.out.printf("[REMOVE DATA : %d]\n", stack.pop(index));
        }

    }

}