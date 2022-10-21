#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#pragma warning(disable:4996)

int* dict;
int n;

/* 메소드 정의 */
void insertItem(int* dict, int idx);
int findElement(int k);
int rFE(int k, int l, int r);
void show(int* dict);

int main() {

	int k, result;

	scanf("%d %d", &n, &k);
	dict = (int*)malloc(sizeof(int) * n);

	for (int i = 0; i < n; i++) {
		insertItem(dict, i);
	}

	result = findElement(k);
	printf(" %d", result);

	free(dict);
	return;
}

void insertItem(int* dict, int idx) {

	int k, j;

	scanf("%d", &k);

	if (idx == 0) {
		dict[0] = k;
	}
	else if (0 < idx) {

		j = idx - 1;
		while (j >= 0 && dict[j] > k) {
			dict[j + 1] = dict[j];
			j -= 1;
		}

		dict[j + 1] = k;
	}

	return;
}

int findElement(int k) {


	return rFE(k, 0, n - 1);

}

int rFE(int k, int l, int r) {

	int mid = (l+r)/2;
	
	if (l > r) return r == 0 ? -1 : r;
	
	if (k == dict[mid]) return mid;
	else if (k < dict[mid]) {
		return rFE(k, l, mid - 1);
	}
	else {
		return rFE(k, mid + 1, r);
	}

}


void show(int* dict) {

	for (int i = 0; i < n; i++) {
		printf(" %d", dict[i]);
	}printf("\n");
}