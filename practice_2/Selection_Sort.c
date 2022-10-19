#include <stdio.h>
#include <stdlib.h>
#pragma warning(disable:4996)

// Define Method
int* InitArray(int n);
void SelectionSort(int n, int* arr);
void swap(int* a, int* b);

int main() {

	int* arr;
	int n;
	scanf("%d", &n);

	arr = InitArray(n);

	// Run Selection Sort
	SelectionSort(n, arr);

	// Run Print Array
	for (int i = 0; i < n; i++) {
		printf(" %d", arr[i]);
	}

	return;

}

int* InitArray(int n) {

	int* arr = (int*)malloc(sizeof(int) * n);

	if (arr == NULL) {
		printf("Error");
		return;
	}

	for (int i = 0; i < n; i++) scanf("%d", &arr[i]);

	return arr;
}

// 시간 복잡도 : O(n^2)
// Data가 정
void SelectionSort(int n, int* arr) { 

	int pass, minLoc;

	for(pass = 0;pass < n - 2;pass++) { 
		
		minLoc = pass;

		for (int j = pass + 1; j < n - 1; j++) { 

			// 리스트에서 가장 작은 Data 검색
			if (arr[j] < arr[minLoc]) {
				minLoc = j;
			}
		}

		// 가장 작은 Data를 앞으로 이동
		swap(arr[pass], arr[minLoc]);
	}

}

void swap(int* a, int* b) {

	int tmp;

	tmp = *a;
	*a = *b;
	*b = tmp;

}