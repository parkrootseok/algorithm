	#include <stdio.h>
	#include <stdlib.h>
	#pragma warning(disable:4996)

	// Define Method
	int* InitArray(int n);
	void InsertionSort(int n, int* arr);

	int main() {

		int* arr;
		int n;
		scanf("%d", &n);

		arr = InitArray(n);

		// Run Insertion Sort
		InsertionSort(n, arr);

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

	void InsertionSort(int n, int* arr) {

		int i, j, save;

		for (i = 1; i < n; i++) {

			save = arr[i];
			
			j = i - 1;
			while (j >= 0 && arr[j] > save) {
				
				arr[j + 1] = arr[j];
				j -= 1; // For Exit first loop
			
			}

			arr[j + 1] = save;

		}


	}