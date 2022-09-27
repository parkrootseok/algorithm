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
	void inPlaceHeapSort();
	void insertItem(int key);
	void upHeap(int i);
	void downHeap(int i);
	void rBuildHeap(int i);
	void printHeap();

	int main() {

		int num, key;

		scanf("%d", &num);
		for (int i = 0; i < num; i++) {
			scanf("%d", &key);
			insertItem(key);
		}

		inPlaceHeapSort(); 	// 제자리 힙 정렬
		n = num;	// 힙 정렬 후 힙 크기 복구

		printHeap();

		return;
	}

	void inPlaceHeapSort() {

		int save;

		rBuildHeap(1); // phase 1 상향식 힙 생성

		for (int i = n; i > 1; i--) {	// phase 2 힙 정렬

			// 힙의 최상위 노드와 마지막 노드 교환
			save = H[1];
			H[1] = H[i];
			H[i] = save;

			n--;

			downHeap(1); // 힙 정렬(swap 작업으로 순서 재정렬 필요)

		}

		return;

	}

	void insertItem(int key) {
	
		H[++n] = key;
		upHeap(n);

	}

	void upHeap(int i) {

		int save;

		if (i == 1) return;

		if (H[i] <= H[i / 2]) return;
	
		save = H[i / 2];
		H[i / 2] = H[i];
		H[i] = save;
	
		upHeap(i / 2);

	}

	void downHeap(int i) {

		int save, bigger;
	
		if (n < i * 2 && n < i * 2 + 1) return;

		bigger = i * 2;

		if (n >= i * 2 + 1) {
			if (H[i * 2 + 1] > H[bigger]) bigger = i * 2 + 1;
		}

		if (H[i] >= H[bigger])	return;

		save = H[i];
		H[i] = H[bigger];
		H[bigger] = save;
	
		downHeap(bigger);

	}

	void rBuildHeap(int i) {

		if (i > n) return;

		rBuildHeap(i * 2);
		rBuildHeap(i * 2 + 1);
	
		downHeap(i);

	}

	void printHeap() {

		for (int i = 1; i <= n; i++) {
			printf(" %d", H[i]);
		} printf("\n");

	}