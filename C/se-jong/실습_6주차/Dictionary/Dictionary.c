#include <stdio.h>
#include <stdlib.h>


typedef struct __dictionary {

    int * key;
    int numOfData;

} Dictionary;

Dictionary * initDictionary(int n) {

    Dictionary * dic = (Dictionary *)malloc(sizeof(Dictionary));
    dic->key = (int *)malloc(sizeof(int) * n);
    dic->numOfData = 0;

    return dic;
}

void input(Dictionary * dic, int n) {

    int k;

    for (int i = 0; i < n; i++) {
        scanf("%d", &k);
        getchar();

        if (k == -1) {
            return;
        }

        dic->key[i] = k;
        (dic->numOfData)++;
    }
}

int linearSearch(Dictionary * dic, int target);
int binarySearch(Dictionary * dic, int l, int r, int target);
int rbinarySearch(Dictionary * dic, int l, int r, int target);


int main() {

    int n, target; 
    
    scanf("%d %d", &n, &target);
    Dictionary *dic = initDictionary(n);
    input(dic, n);

    printf("linear : %d\n",linearSearch(dic, target)); 
    printf("binary : %d\n", binarySearch(dic, 0, n - 1, target)); 
    printf("rBinary : %d\n", rbinarySearch(dic, 0, n - 1, target));

    return 0;
}

int linearSearch(Dictionary * dic, int target) {   

    for (int i = 0; i < dic->numOfData; i++) {
        if (dic->key[i] == target) {
            return i;
        }
    }

    return -1;

}

int binarySearch(Dictionary * dic, int l, int r, int target) {

    int s = l, e = r;    
    int mid;

    while (s <= e) {

        mid = (s + e) / 2;

        if (target == dic->key[mid]) {
            return mid;
        }
        else if (target < dic->key[mid]) {
            e = mid - 1;
        } else {
            s = mid + 1;
        }

    }

    return s == dic->numOfData? dic->numOfData : s;

}


int rbinarySearch(Dictionary * dic, int l, int r, int target) {

    if (l > r) {

        if (dic->key[r] <= target) {
            return r;
        }

        return -1;
    }

    int mid = (l + r) / 2;

    if(target == dic->key[mid]) {
        return mid;
    } else if (target < dic->key[mid]) {
        return rbinarySearch(dic, l, mid - 1, target);
    } else {
        return rbinarySearch(dic, mid + 1, r, target);
    }

}