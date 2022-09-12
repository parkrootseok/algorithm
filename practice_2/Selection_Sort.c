#include <stdio.h>
#include <stdlib.h>
#pragma warning(disable:4996)

// 메소드 정의
int* InitArray(int n);
void SelectionSort(int n, int* arr);

int main() {

	int* arr;
	int n;
	scanf("%d", &n);

	arr = InitArray(n);

	// Selection-Sort
	SelectionSort(n, arr);

	// Print Array
	for (int i = 0; i < n; i++) {
		printf(" %d", arr[i]);
	}

	return;

}

int* InitArray(int n) {

	int* arr = (int*)malloc(sizeof(int));

	if (arr == NULL) {
		printf("Error");
		return;
	}

	for (int i = 0; i < n; i++) {
		scanf("%d", &arr[i]);
	}
		
	return arr;
}

void SelectionSort(int n, int* arr) {

	int idx, num, tmp;

	for (int i = n - 1;0 <= i; i--) {

		idx = i;

		// 최댓값 탐색
		for (int j = 0; j < i; j++) {

			if (arr[idx] < arr[j]) {
				idx = j;
			}
			
		}

		//교환
		if (idx != i) {
			tmp = arr[i];
			arr[i] = arr[idx];
			arr[idx] = tmp;
		}
	}

	return;

}