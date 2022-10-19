#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#pragma warning(disable:4996)

/* 메소드 정의 */
void swap(int* a, int* b);
int findPivot(int* arr, int l, int r);
void inPlaceQuickSort(int* arr, int l, int r);
int inPlacePartition(int* arr, int l, int r, int k);

int main() {

	int* arr;
	int n;

	scanf("%d", &n);
	arr = (int*)malloc(sizeof(int) * n);

	for (int i = 0; i < n; i++) {
		scanf("%d", arr + i);
	}
	
	inPlaceQuickSort(arr, 0, n - 1);

	for (int i = 0; i < n; i++) {
		printf(" %d", *(arr + i));
	}

}

void swap(int* a, int* b) {

	int tmp;
	tmp = *a;
	*a = *b;
	*b = tmp;

}

int findPivot(int* arr, int l, int r) {

	int randNum;
	
	srand(time(NULL));
	randNum = rand() % r + 1;

	return randNum;
}

void inPlaceQuickSort(int* arr, int l, int r) {
	int k, a, b, i;

	if (l >= r) return;

	k = findPivot(arr, l, r);
	printf("%d\n", k);
	a = inPlacePartition(arr, l, r, k);
	b = inPlacePartition(arr, l, r, k);
	
	inPlaceQuickSort(arr, l, a - 1);
	inPlaceQuickSort(arr, b + 1, r);
	
}

int inPlacePartition(int* arr, int l, int r, int k) {

	int pivot, i, j;

	pivot = arr[k];
	//printf("pivot : %d\n", pivot);
	swap(arr + k, arr + r);
	i = l;
	j = r - 1;

	while (i <= j) {

		while (i <= j & arr[i] <= pivot) {
			i += 1;
		}
		while (i <= j & arr[j] >= pivot) {
			j -= 1;
		}

		if (i < j) swap(arr + i, arr + j);
		
		// printf("i : %d j : %d \n", i, j);
	}
	// printf("exit\n");
	swap(arr + i, arr + r);

	return i;

}