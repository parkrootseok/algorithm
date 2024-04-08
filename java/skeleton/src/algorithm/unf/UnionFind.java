package algorithm.unf;

/**
 * 서로소 집합
 * @author parkrootseok
 *
 * - 서로 교집합이 없는 집합을 서로소 집합
 * - 원소의 대표자를 통해 서로 속한 그룹이 동일한지 확인
 * - 필요한 연산은 3가지
 *  -> make
 *  -> find
 *  -> union
 *
 *
 */
public class UnionFind {

    static final int ELEMENT_NUMBER = 5;
    static int[] unf;
    static int[] rank;

    public static void main(String[] args) {

    }

    public static void make() {

        unf = new int[ELEMENT_NUMBER + 1];
        rank = new int[ELEMENT_NUMBER + 1];

        // 1. 자기 자신을 대표로 가지는 집합을 생성
        for (int element = 1; element <= ELEMENT_NUMBER; element++) {
            unf[element] = element;
        }

    }

    public static int find(int element) {

        // 1. 찾는 원소가 속한 집합의 대표라면 반환
        if (element == unf[element]) {
           return element;
        }

        // 2. 아니라면 자신이 속해있는 대표를 탐색
        // 추가적으로 현재 자신의 대표자를 변경하여 경로 압축을 실행
        return unf[element] = find(unf[element]);

    }

    public static void union(int elementA, int elementB) {

        // 1. 각 원소가 속한 집합의 대표 탐색
        int representationA = find(elementA);
        int representationB = find(elementB);

        // 2. 대표가 같다면 이미 같은 집합이므로 종료
        if (representationA == representationB) {
            return;
        }

        // 3. 대표가 같지 않고 A 원소가 속한 집합의 랭크가 더 높다면 A 밑으로 B를 이동 후 종료
        if (rank[representationA] > unf[representationB]) {
            unf[representationB] = representationA;
            return;
        }

        // 4. B 원소가 속한 랭크다 더 크다면 B가 더 높다면 A를 이동
        unf[representationA] = representationB;

        // 5. 두 집합의 랭크가 같다면 밑으로 들어간 집합의 랭크를 증가
        if (rank[representationA] == rank[representationB]) {
            unf[representationB]++;
        }

    }


}
