	#include <stdio.h>
	#include <stdlib.h>
	#pragma warning(disable:4996)

	// 메소드 정의
	int* InitArray(int n);
	void InsertionSort(int n, int* arr);

	int main() {

		int* arr;
		int n;
		scanf("%d", &n);

		arr = InitArray(n);

		// Insertion Sort
		InsertionSort(n, arr);

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

		for (int i = 0; i < n; i++) scanf("%d", &arr[i]);
	

		return arr;
	}

	void InsertionSort(int n, int* arr) {

		int tmp, i, j;

		for (i = 1; i < n; i++) {

			tmp = arr[i];
		
			for (j =  i - 1; j >= 0 && arr[j] > tmp; j--) {
				arr[j + 1] = arr[j];
			}

			arr[j + 1] = tmp;

		}

		return;

	}