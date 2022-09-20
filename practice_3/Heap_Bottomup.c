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
void rBuildHeap(int i);
void BuildHeap();
void downHeap(int i);
void printHeap();

int main() {

	int num, e;

	scanf("%d", &num);

	for (int i = 1; i <= num; i++) {
		
		scanf("%d", &e);
		H[i] = e;
		n++;
	}

	//rBuildHeap(1);
	BuildHeap();
	printHeap();

	return;

}

void rBuildHeap(int i) { // 재귀적 방식

	// 인덱스가 트리 크기보다 클 경우 종료
	if (i > n) return;
	
	// 자식 노드 조정
	rBuildHeap(i * 2);
	rBuildHeap(i * 2 + 1);
	
	// 부모 노드 순서 조정
	downHeap(i);

	return;

}

void BuildHeap() { // 비재귀적 방식
	
	// 가장 깊은 내부 노드 중 오른쪽 노드에서 시작
	// 리프 노드는 조사할 필요 X
	for (int i = n / 2; i > 0 ; i--) {
		downHeap(i);
	}
	
}

void downHeap(int i) {

	int tmp, bigger;

	// 내부 노드 존재 확인
	if (n < (i * 2) && n < (i * 2 + 1)) return;

	// 존재 시 내부 노드 중 가장 큰 노드 찾기
	bigger = i * 2;

	if (n >= i * 2 + 1) {

		if (H[i * 2 + 1] > H[bigger]) {

			bigger = i * 2 + 1;

		}

	}

	// 상위 노드가 내부 노드와 크거나 작으면 교환 필요 X
	if (H[i] >= H[bigger]) return;

	// 스왑
	tmp = H[i];
	H[i] = H[bigger];
	H[bigger] = tmp;

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