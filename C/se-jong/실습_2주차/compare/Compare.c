#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 1000000    // Data 갯수 정의

void insertionSort(long size, long * arr);
void selectionSort(long size, long * arr);

int main() {   

    // 시간 측정에 사용할 변수 선언
    clock_t finish, start;  
    double duration;

    srand(time(NULL));  // 랜덤 변수 생성에 사용하기 위해 초기화

    printf("수행할 데이터 갯수 : %d\n", N);

    long *arr = (long *)malloc(sizeof(long) * N);

    for (long i = 0; i < N; i++) {   // Data 초기화
        // *(arr + i) = (i + 1);       // 정순
        // *(arr + i) = (N - i);   // 역순
        *(arr + i) = rand();        // 랜덤
    }

    printf("-------------선택 정렬-------------\n");
    start = clock();
    selectionSort(N, arr);
    finish = clock();
    duration = (double)(finish-start)/CLOCKS_PER_SEC;
    printf("걸린 시간 : %.3lf sec\n", duration);

    for (long i = 0; i < N; i++) {   // Data 초기화
        // *(arr + i) = (i + 1);       // 정순
        // *(arr + i) = (N - i);   // 역순
        *(arr + i) = rand();        // 랜덤
    }

    printf("-------------삽입 정렬-------------\n");
    start = clock();
    insertionSort(N, arr);
    finish = clock();
    duration = (double)(finish-start)/CLOCKS_PER_SEC;
	printf("걸린 시간 : %.3lf sec\n", duration);

    return 0;

}

void insertionSort(long size, long *arr) {

    long i, j;
    long cur;

    for (i = 1; i < size; i++)
    {

        cur = *(arr + i);

        for (j = i - 1; 0 <= j; j--) {

            if (*(arr + j) < cur) { // 현재보다 작은 원소를 발견하면 중지하고
                break;
            }

            // 그렇지 않으면 뒤로 이동
            *(arr + (j + 1)) = *(arr + j);

        }

        // 작은 원소 뒤에 삽입
        *(arr + (j + 1)) = cur;
    }
}

void selectionSort(long size, long *arr) {

    long tmp, idx;
    for (long i = 0; i < size ; i++) {

        idx = i;
        for (long j = i + 1; j < size; j++) {

            if (*(arr + idx) > *(arr + j)) {    // i보다 제일 작은 위치를 검색하고
                idx = j;
            }

        }
        
        // arr[i]와 arr[idx]를 교환
        tmp = *(arr + i);
        *(arr + i) = *(arr + idx);
        *(arr + idx) = tmp;
    }

}