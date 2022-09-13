#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <Windows.h>
#pragma warning(disable:4996)

int * InitArray(int n);
void SelectionSort(int n, int* arr);
void InsertionSort(int n, int* arr);
void show(int n, int* arr);

int main() {

	LARGE_INTEGER ticksPerSec;
	LARGE_INTEGER start, end, diff;

	int n; scanf("%d", &n);

	int* Selection_arr, * Insertion_arr;

	Selection_arr = InitArray(n);
	Insertion_arr = InitArray(n);

	// Run Selection Sort and Check Time
	QueryPerformanceFrequency(&ticksPerSec);
	QueryPerformanceCounter(&start);
	SelectionSort(n, Selection_arr);
	QueryPerformanceCounter(&end);

	diff.QuadPart = end.QuadPart - start.QuadPart;
	printf("%.12f ms\n", ((double)diff.QuadPart / (double)ticksPerSec.QuadPart) * 1000);

	// Run Insertion Sort and Check Time
	QueryPerformanceFrequency(&ticksPerSec);
	QueryPerformanceCounter(&start);
	InsertionSort(n, Insertion_arr);
	QueryPerformanceCounter(&end);

	diff.QuadPart = end.QuadPart - start.QuadPart;
	printf("%.12f ms\n", ((double)diff.QuadPart / (double)ticksPerSec.QuadPart) * 1000);
	
	// Print Array
	//show(n, Selection_arr);
	//show(n, Insertion_arr);

}



int* InitArray(int n) {

	int * arr = (int*)malloc(sizeof(int) * n);

	srand(time(NULL));
	for (int i = 0; i < n; i++) {	
		
		// Add array element
		arr[i] = rand();

	}

	return arr;
}

void SelectionSort(int n, int* arr) {

	int tmp, idx;

	for (int i = n - 1; 0 <= i; i--) {

		idx = i;

		// ÃÖ´ñ°ª Å½»ö
		for (int j = 0; j < i; j++) {

			if (arr[idx] < arr[j]) {
				idx = j;
			}

		}

		//±³È¯
		if (idx != i) {
			tmp = arr[i];
			arr[i] = arr[idx];
			arr[idx] = tmp;
		}

	}

	return;

}
void InsertionSort(int n, int* arr) {

	int i, j, save;

	for (i = 1; i < n; i++) {

		save = arr[i];

		j = i - 1;
		while (j >= 0 && arr[j] > save) {
			arr[j + 1] = arr[j];
			j -= 1;
		}
		
		arr[j + 1] = save;
	
	}

	return;

}

void show(int n, int* arr) {

	for (int i = 0; i < n; i++) {
		printf(" %d", arr[i]);
	}printf("\n");

}