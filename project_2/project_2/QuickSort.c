#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <windows.h>
#pragma warning(disable:4996)

/* 전역 변수 선언 */
int n = 100000;
int Limits[] = { 1, 100, 500, 1000 };

/* 메소드 정의 */
int * createArray();
void swap(int* a, int* b);
void show(int* L);

int findPivot(int* L, int l, int r, int mode);
void insertionSort(int* L);
void inPlaceQuickSort(int* L, int l, int r, int limit, int mode);
int inPlacePartirion(int* L, int l, int r, int k);

int main() {

	LARGE_INTEGER ticksPerSec;
	LARGE_INTEGER start, end, diff;

	int *arr, *L;

	// 배열 생성
	arr = createArray();
	
	for (int i = 0; i < 4; i++) {
		
		printf("Limit : %d\t", Limits[i]);
	
		
		for (int mode = 0; mode < 4; mode++) {

			L = (int*)malloc(sizeof(int) * n);
			memcpy(L, arr, sizeof(int) * n); // 배열 복사
			
			QueryPerformanceFrequency(&ticksPerSec);
			QueryPerformanceCounter(&start); // 시작 시간 측정
			inPlaceQuickSort(L, 0, n - 1, Limits[i], mode);
			QueryPerformanceCounter(&end); // 끝 시간 측정
			diff.QuadPart = end.QuadPart - start.QuadPart; // 총 수행 시간 계산
			printf("time: %.12f\t", ((double)diff.QuadPart / (double)ticksPerSec.QuadPart) * 1000); // CPUtime 출력
			
			free(L);
		}printf("\n");

	} free(arr);
	return;
}

int * createArray() {

	int* L;
	int randNum;

	L = (int*)malloc(sizeof(int) * n);

	srand(time(NULL));
	for (int i = 0; i < n; i++) {
		randNum = (rand() % n) + 1;
		L[i] = randNum;
	}

	return L;
}

void swap(int* a, int* b) {

	int tmp;
	
	tmp = *a;
	*a = *b;
	*b = tmp;
}


void show(int * L) {

	for (int i = 0; i < n; i++) {
		printf(" %d", L[i]);
	} printf("\n");
}

/*
결정적 1 : 첨자 r 반환
결정적 3 : 부배열의 크기가 3 미만일 경우 앞 위치 반환, 그렇지 않을 경우 맨 앞, 중간, 맨 뒤 원소 중 3-중앙값 반환
무작위 1 : l과 r 사이의 무작위 위치 반환
무작위 3 : 부배열의 크기가 3 미만일 경우 앞 위치 반환, 그렇지 않을 경우 무작위 원소 중 3-중앙값 반환
*/

int findPivot(int* L, int l, int r, int mode) {

	//printf("----피봇 찾기 시작----\n");

	int result, ll, mm, rr;
	
	srand(time(NULL));
	switch (mode) { // 0 : 결정적 1, 1 : 결정적 3, 2 : 무작위 1, 3: 무작위 3 
	
	case 0:	// 결정적 1 - 첨자 r을 반환
		//printf("----피봇 찾기 : 결정적 1----\n");
		result = r; 
		break;
	
	case 1: // 결정적 3 - 맨 앞, 중간, 맨 뒤 원소 중 3-중앙값 반환 
		//printf("----피봇 찾기 : 결정적 3----\n");
		ll = l; mm = (int)(r / 2 + 0.5);  rr = r;

		if (L[ll] <= L[mm] && L[mm] <= L[rr] || L[ll] >= L[mm] && L[mm] >= L[rr]) // 중간값이 mm 일 때
			result = mm;
		else if (L[mm] <= L[ll] && L[ll] <= L[rr] || L[mm] >= L[ll] && L[ll] >= L[rr]) // 중간값이 ll 일 때
			result = ll;
		else // 중간값이 rr 일 때
			result = rr;
		break;
	
	case 2: // 무작위 1 - l ~ r 사이의 난수 반환
		//printf("----피봇 찾기 : 무작위 1----\n");
		result = rand() % (r - l + 1) + l;
		break;
	
	case 3:// 무작위 3 - 무작위 원소 중 3-중앙값 반환
		//printf("----피봇 찾기 : 무작위 3----\n");
		ll = rand() % (r - l + 1) + l; mm = rand() % (r - l + 1) + l; rr = rand() % (r - l + 1) + l;

		if (L[ll] <= L[mm] && L[mm] <= L[rr] || L[ll] >= L[mm] && L[mm] >= L[rr]) // 중간값이 mm 일 때
			result = mm;
 		else if (L[mm] <= L[ll] && L[ll] <= L[rr] || L[mm] >= L[ll] && L[ll] >= L[rr]) // 중간값 ll 일 때
			result = ll;
		else // 중간값이 rr 일 때
			result = rr;
		break;
	}

	return result; // 피봇 반환
}

void insertionSort(int* L) {
	
	int j, pass, save;

	for (pass = 1; pass < n; pass++) {
		
		save = L[pass];
		j = pass - 1;
		
		while (j >= 0 && L[j] > save) {
			L[j + 1] = L[j];
			j -= 1;
		}
		L[j + 1] = save;
	}

	return;
}

void inPlaceQuickSort(int* L, int l, int r, int limit, int mode) {
	
	
	int k, b, size = r - l + 1;

	if (l >= r) return;

	if (limit >= size) { // Limit >= 부배열 크기일때 작을 때 삽입 정렬 수행 
		insertionSort(L); 
	}
	else { // 그 외에 경우 퀵 정렬 수행

		k = findPivot(L, l, r, mode);
		b = inPlacePartirion(L, l, r, k);


		inPlaceQuickSort(L, l, b - 1, limit, mode);
		inPlaceQuickSort(L, b + 1, r, limit, mode);
	}

}

int inPlacePartirion(int* L, int l, int r, int k) {
	
	int pivot, i, j;
	
	pivot = L[k];
	i = l;
	j = r - 1;

	swap(L + r, L + k); // 피봇은 정렬할 필요가 없으므로 배열 맨 끝으로 이동
	
	while (i <= j) {
		
		while (i <= j && L[i] < pivot) i++; // pivot보다 큰 원소 위치 검색
		
		while (i <= j && L[j] >= pivot) j--; // pivot보다 큰 검색 위치 검색

		if (i < j) swap(L + i, L + j);	// 교차하지 않음은 정렬해야할 원소가 존재 하다는 의미이므로  swap
	
	}

	swap(L + i, L + r);

	return i;

}