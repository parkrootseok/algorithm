#include <stdio.h>
#include <stdlib.h>

int * initArray(int);
void input(int n, int *dic);
int rBinarySearch(int * dic, int start, int end, int target);

int main() {

    int *dic;
    int n, target;
    
    scanf("%d %d", &n, &target);
    
    dic = initArray(n);
    input(n, dic);
    printf(" %d\n", rBinarySearch(dic, 0, n - 1, target));

    return 0;

}

int * initArray(int n) {
    return (int *)malloc(sizeof(int) * n);
}

void input(int n, int * dic) {

    int key;

    for (size_t i = 0; i < n; i++) {
        scanf("%d", &key);
        dic[i] = key;
    }

}

int rBinarySearch(int * dic, int start, int end, int target) {

    if (start > end) {

        if (dic[end] <= target) {
            return end;
        }

        return -1;

    }

    int mid = (start + end) / 2;

    if (target == dic[mid]) {
        return mid;
    } else if (target < dic[mid]) {
        return rBinarySearch(dic, start, mid - 1, target);
    } else {
        return rBinarySearch(dic, mid + 1, end, target);
    }
    
}