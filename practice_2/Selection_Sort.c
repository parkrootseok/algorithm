#include <stdio.h>
#include <stdlib.h>
#pragma warning(disable:4996)

// Define Method
int* InitArray(int n);
void SelectionSort(int n, int* arr);

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

void SelectionSort(int n, int* arr) {

	int i, j, idx, tmp;
	for (i = n - 1; i >= 0 ; i--) {

		idx = i;

		for (j = 0; j < i; j++) {

			// Find Max
			if (arr[idx] < arr[j]) {
				idx = j;
			}

		}

		// Swap
		if (idx != i) {
			tmp = arr[i];
			arr[i] = arr[idx];
			arr[idx] = tmp;
		}
	}
	

}