package traversal;

/**
 * 이진트리 순회
 * @author parkrootseok
 *
 * - 순회에는 3가지 방법이 존재
 * 1. 전위 순회
 * 2, 중위 순회
 * 3. 후위 순회
 **/
public class Traversal {

    public static void main(String[] args) {

    }

    /**
     * 전위 순회
     */
    public void preOrder(int parent) {

        int leftChild = parent * 2;
        int rightChild = parent * 2 + 1;

        // 1. 부모 노드 로직 수행
        System.out.println(parent);

        // 2. 좌측 자식 노드 방문
        preOrder(leftChild);

        // 3. 우측 자식 노드 방문
        preOrder(rightChild);

    }

    /**
     * 중위 순회
     */
    public void inOrder(int parent) {

        int leftChild = parent * 2;
        int rightChild = parent * 2 + 1;

        // 1. 좌측 자식 노드 방문
        inOrder(leftChild);

        // 2. 부모 노드 로직 수행
        System.out.println(parent);

        // 3. 우측 자식 노드 방문
        inOrder(rightChild);
    }

    /**
     * 후위 순회
     */
    public void postOrder(int parent) {

        int leftChild = parent * 2;
        int rightChild = parent * 2 + 1;

        // 1. 좌측 자식 노드 방문
        postOrder(leftChild);

        // 2. 우측 자식 노드 방문
        postOrder(rightChild);

        // 3. 부모 노드 로직 수행
        System.out.println(parent);

    }

}
