#include <stdio.h>
#include <stdlib.h>

void selectionSort(int size, int * arr);

int main()
{

    int n;
    scanf("%d", &n);

    int * arr = (int *)malloc(sizeof(int) * n);
    for (int i = 0; i < n; i++) {
        scanf("%d", arr + i);
    }

    selectionSort(n, arr);

    for (int i = 0; i < n; i++) {
		printf(" %d", *(arr + i));
	}

    return 0;
}

void selectionSort(int size, int *arr) {

    int tmp, idx;
    for (int i = 0; i < size ; i++) {

        idx = i;
        for (int j = i + 1; j < size; j++) {

            if (*(arr + idx) > *(arr + j)) {
                idx = j;
            }

        }

        tmp = *(arr + i);
        *(arr + i) = *(arr + idx);
        *(arr + idx) = tmp;
    }

}