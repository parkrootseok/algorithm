#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

/* 셀 상태에 대한 ENUM */
typedef enum Status { 
    EMPTY, ACTIVE, INACTIVE 
} Status;

/* 셀 구조체 정의 */
typedef struct __cell { 
    int element;        // 저장된 원소
    Status status;      // 셀의 상태
} Cell;

typedef struct __hashtable {
    Cell *cells;    // 저장된 셀에 대한 배열
} HaseTable;

HaseTable *initHashTable(int M);

int hashFunction(int x, int M);
int getNextBucket(int v, int i, int M);

bool insertItem(HaseTable * ht, int x, int M);
bool findElement(HaseTable * ht, int x, int M);
bool removeElement(HaseTable * ht, int x, int M);

int main() {

    HaseTable *ht;
    int M, n;

    scanf("%d", &M);
    getchar();

    ht = initHashTable(M);    

    while (true) {
        
        char oper;
        int x;

        scanf("%c", &oper);
        getchar();

        if (oper == 'e') {  // e를 입력 받았으면
             // 모든 cell과 hashtable에 대한 메모리 할당 해제 후 프로그램 종료
            free(ht->cells);    
            free(ht);          
            return 0;
        }

        switch (oper) {
        case 'i':
            scanf("%d", &x);
            if (!insertItem(ht, x, M)) {
                printf("-1\n");
            }
            getchar();
            break;
        case 's':
            scanf("%d", &x);
            if (!findElement(ht, x, M)) {
                printf("-1\n");
            }
            getchar();
            break;
        case 'd':
            scanf("%d", &x);
            if (!removeElement(ht, x, M)) {
                printf("-1\n");
            }
            getchar();
            break;
        }

    }

}

/* HashTable 초기화 메소드 */
HaseTable * initHashTable(int M) {  

    HaseTable *ht = (HaseTable *)malloc(sizeof(HaseTable));

    ht->cells = (Cell *)malloc(sizeof(Cell) * M);   // M개 만큼 할당
    for (int i = 0; i < M; i++) {
        ht->cells[i].status = EMPTY;    // M개의 셀들의 상태를 EMPTY로 초기화
    }

    return ht;

}

/* 1차 해쉬 */
int hashFunction(int x, int M) {
    return x % M;
}

/* 2차 해쉬(선형조사법을 사용) */
int getNextBucket(int v, int i, int M) {
    return (v + i) % M;
}

/* 아이템 삽입 */
bool insertItem(HaseTable * ht, int x, int M) {

    int v = hashFunction(x, M); // 1차 해쉬를 이용해 저장할 주소를 지정
    int i, b;

    i = 0;
    while (i < M) { // 모든 테이블을 방문하기 전 동안

        b = getNextBucket(v, i, M); // 2차 해쉬를 통해 저장할 주소를 지정하고

        if(ht->cells[b].status != ACTIVE) { // 저장할 주소의 상태가 미사용중이라면 
            
            // 데이터를 삽입 후(원소, 활성화 상태로 변경)
            ht->cells[b].element = x;
            ht->cells[b].status = ACTIVE;

             // 저장된 주소를 출력 후 종료
            printf("%d\n", b); 
            return true;

        } else {    // 사용중이라면 충돌이 발생했음을 알리고 다시 탐색 시작

            printf("C"); 
            i++;
        
        }

    }

    return false;
}

bool findElement(HaseTable * ht, int x, int M) {

    int v = hashFunction(x, M); // 1차 해쉬를 이용해 탐색할 주소를 지정
    int i, b;

    i = 0;
    while (i < M) { // 모든 테이블을 탐색하기 전 동안

        b = getNextBucket(v, i, M); // 2차 해쉬를 통해 탐색할 주소를 지정하고

        if(ht->cells[b].status == EMPTY) {  // 비어있다면 종료
            
            return false;

        } else  if (ht->cells[b].status == ACTIVE && ht->cells[b].element == x) {   // 찾는 원소와 동일하다면
        
            // 현재 주소를 출력하고 종료
            printf("%d\n", b);
            return true;

        } else {    // 이미 사용중이라면 충동을 표시하고 다시 탐색 시작

            printf("C");
            i++;
        
        }
    
    }

    return false;

}

bool removeElement(HaseTable * ht, int x, int M) {

    int v = hashFunction(x, M); // 1차 해쉬를 이용해 탐색할 주소를 지정
    int i, b;

    i = 0;
    while (i < M) { // 모든 테이블을 탐색하기 전 동안

        b = getNextBucket(v, i, M); // 2차 해쉬를 통해 탐색할 주소를 지정하고

        if(ht->cells[b].status == EMPTY) {  // 비어있다면 종료

            return false;
        
        } else if (ht->cells[b].status == ACTIVE && ht->cells[b].element == x) {    // 삭제할 원소와 동일하다면
            
            // 셀을 비활성화 후 종료(비파괴적 방법)
            ht->cells[b].status = INACTIVE; 

            // 현재 주소를 출력 후
            printf("%d\n", b);

            return true;

         } else {   // 이미 사용중이고 원소가 다르다면 충동을 표시하고 다시 탐색 시작

            printf("C");
            i++;
        
        }

    }

    return false;

}