#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <Windows.h>
#pragma warning(disable:4996)

int * InitArray(int n);
void reverseSort(int n, int arr);
void SelectionSort(int n, int* arr);
void InsertionSort(int n, int* arr);
void show(int n, int* arr);

int main() {

	LARGE_INTEGER ticksPerSec;
	LARGE_INTEGER start, end, diff;

	int n; scanf("%d", &n);

	int* Selection_arr, * Insertion_arr;

	for (int i = 0; i < 10; i++) {

		Selection_arr = InitArray(n);
		Insertion_arr = InitArray(n);

		printf("%d번 째 반복\n", i + 1);

		// A. 정렬이 안 된 데이터 배열
		printf("A. 정렬이 안 된 데이터\n");

		// Run Selection Sort and Check Time
		QueryPerformanceFrequency(&ticksPerSec);
		QueryPerformanceCounter(&start);
		SelectionSort(n, Selection_arr);
		QueryPerformanceCounter(&end);

		diff.QuadPart = end.QuadPart - start.QuadPart;
		printf("Selection Sort : %.12f ms\n", ((double)diff.QuadPart / (double)ticksPerSec.QuadPart) * 1000);

		// Run Insertion Sort and Check Time
		QueryPerformanceFrequency(&ticksPerSec);
		QueryPerformanceCounter(&start);
		InsertionSort(n, Insertion_arr);
		QueryPerformanceCounter(&end);

		diff.QuadPart = end.QuadPart - start.QuadPart;
		printf("Insertion Sort : %.12f ms\n", ((double)diff.QuadPart / (double)ticksPerSec.QuadPart) * 1000);



		// B. 정렬된 데이터 배열
		printf("B. 정렬된 데이터\n");

		// Run Selection Sort and Check Time
		QueryPerformanceFrequency(&ticksPerSec);
		QueryPerformanceCounter(&start);
		SelectionSort(n, Selection_arr);
		QueryPerformanceCounter(&end);

		diff.QuadPart = end.QuadPart - start.QuadPart;
		printf("Selection Sort : %.12f ms\n", ((double)diff.QuadPart / (double)ticksPerSec.QuadPart) * 1000);

		// Run Insertion Sort and Check Time
		QueryPerformanceFrequency(&ticksPerSec);
		QueryPerformanceCounter(&start);
		InsertionSort(n, Insertion_arr);
		QueryPerformanceCounter(&end);

		diff.QuadPart = end.QuadPart - start.QuadPart;
		printf("Insertion Sort : %.12f ms\n", ((double)diff.QuadPart / (double)ticksPerSec.QuadPart) * 1000);


		// C. 역순으로 정렬된 데이터 배열
		printf("C. 역순으로 정렬된 데이터\n");
		reverseSort(n, Selection_arr);
		reverseSort(n, Insertion_arr);

		// Run Selection Sort and Check Time
		QueryPerformanceFrequency(&ticksPerSec);
		QueryPerformanceCounter(&start);
		SelectionSort(n, Selection_arr);
		QueryPerformanceCounter(&end);as

		diff.QuadPart = end.QuadPart - start.QuadPart;
		printf("Selection Sort : %.12f ms\n", ((double)diff.QuadPart / (double)ticksPerSec.QuadPart) * 1000);

		// Run Insertion Sort and Check Time
		QueryPerformanceFrequency(&ticksPerSec);
		QueryPerformanceCounter(&start);
		InsertionSort(n, Insertion_arr);
		QueryPerformanceCounter(&end);

		diff.QuadPart = end.QuadPart - start.QuadPart;
		printf("Insertion Sort : %.12f ms\n", ((double)diff.QuadPart / (double)ticksPerSec.QuadPart) * 1000);

	}
	

	return;

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

void reverseSort(int n, int * arr) {

	int tmp;
	for (int i = 0; i < n; i++) {

		for (int j = i; j < n; j++) {

			if (arr[j] < arr[j + 1]) {
				tmp = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = tmp;
			}

		}
	}


}

void SelectionSort(int n, int* arr) {

	int tmp, idx;

	for (int i = n - 1; 0 <= i; i--) {

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