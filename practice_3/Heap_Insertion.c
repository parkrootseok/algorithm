#include <stdio.h>
#include <stdlib.h>
#pragma warning(disable:4996)

#define MAX_SIZE 100

/* 조건 */
// 1. 순차힙
// 2. 최대힙(삭제 작업 시 최대값 삭제)
// 3. 배열 인덱스 0 사용 X

/* 전역 변수 선언*/
int H[MAX_SIZE];		// 힙
int n = 0;				// 힙의 크기

/* 메소드 정의*/
void insertItem(int key);
int removeMax();
void upHeap(int i);
void downHeap(int i);
void printHeap();

int main() {

	int key;
	char oper;

	while (1) {

		scanf("%c", &oper); getchar();

		switch (oper) {
		case 'i':
			scanf("%d", &key); getchar();
			insertItem(key);
			break;
		case 'd':
			printf("%d\n", removeMax());
			break;
		case 'p':
			printHeap();
			break;
		case 'q':
			return;
			break;
		}
	}

	return;

}

void insertItem(int key) { // 힙 삽입

	H[++n] = key;
	upHeap(n);	// 힙 순서 조정

	printf("0\n");

}

int removeMax() {

	int key = H[1];
	H[1] = H[n--];
	downHeap(1);// 힙 순서 조정

	return key;
}

void upHeap(int i) {

	int tmp;

	// 최상위 노드일 경우 종료
	if (i == 1) return;

	// 부모 노드가 더 클 경우 종료
	if (H[i / 2] >= H[i]) return;

	// 위 모든 경우가 아닌 경우 스왑
	tmp = H[i / 2];
	H[i / 2] = H[i];
	H[i] = tmp;

	upHeap(i / 2);
}

void downHeap(int i) {

	int tmp, bigger;

	// 자식 노드 존재 확인
	if ((n < i * 2) & (n < i * 2 + 1)) return;

	bigger = i * 2;

	// 내부 노드 중 제일 큰 값 찾기
	// 최대힙이므로 큰 값을 찾는다. 만약 최소힙이라면 작은 값을 찾아 조정. 
	if ((n >= i * 2 + 1)) {
		if (H[i * 2 + 1] > H[bigger]) {
			bigger = i * 2 + 1;
		}
	}

	// 부모 노드보다 작거나 같을 경우 조정 필요 X
	if (H[i] >= H[bigger]) return;

	tmp = H[i];
	H[i] = H[bigger];
	H[bigger] =tmp;
	
	downHeap(bigger);
}

void printHeap() {

	if (n < 1) {
		return;
	}

	for (int i = 1; i <= n; i++) {
		printf(" %d", H[i]);
	} printf("\n");

}