#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {

    int key;
    struct __node * left;
    struct __node * right;
    struct __node * parent;

} Node;

typedef struct __heap {

    Node * root;    // 루트 노드
    Node * last;    // 마지막 노드

} Heap;

Heap * initHeap() {
    
    Heap *h = (Heap *)malloc(sizeof(Heap)); // 힙 할당
    h->root = (Node *)malloc(sizeof(Node)); // 루트 노드 할당
    h->last = h->root;  // 루트 노드를 마지막 노드로 초기화
    return h;
}

void swapElements(Node * a, Node * b) {
    
    int tmp = a->key;
    a->key = b->key;
    b->key = tmp;

}

int key(Node * v) {
    return v->key;
}

Node * parent(Node *v) {
    return v->parent;
}

Node *leftChild(Node * v) {
    return v->left;
}

Node *rightChild(Node *v) {
    return v->right;
}

Node * sibling(Node * v) {

    Node *p = parent(v);

    if (leftChild(p) == v){
        return rightChild(p);
    }

    return leftChild(p);

}

bool isRoot(Node * v) {

    if (parent(v) == NULL) {
        return true;
    }

    return false;

}

bool isExternal(Node *v) {

    if (v->key == - 1) {
        return true;
    }

    return false;

}

bool isInternal(Node *v) {

        if (v->key != - 1) {
        return true;
    }

    return false;


}

bool insertItem(int key, Heap * h);
int removeMax(Heap * h);

void expandExternal(Node *z);
Node *reduceExternal(Node *z);

Node * advanceLast(Node * v);
Node * retreatLast(Node * v);

void upHeap(Node *v);
void downHeap(Node *v);

void printHeap(Node * root);

void deleteNode(Node * root);
void deleteHeap(Heap * h);

int main() {

    Heap *heap = initHeap();

    int key;
    char oper;

    while (1) {

        scanf("%c", &oper);
        getchar();

        switch (oper) {
        case 'i':
            scanf(" %d", &key);
            getchar();

            if (insertItem(key, heap)) {
                printf("0\n");
            }            
            break;

        case 'd':
            printf("%d\n", removeMax(heap));
            break;

        case 'p':
            printHeap(heap->root);
            printf("\n");
            break;

        case 'q':
            deleteHeap(heap);
            return 0;
        }

    }

}

bool insertItem(int key, Heap * h) {

    // 마지막 노드의 오른쪽으로 전진 후
    Node * z = advanceLast(h->last);
    
    // 전진한 곳에 현재 키를 삽입한 후 마지막 노드로 설정하고
    z->key = key;
    h->last = z;

    // 외부 노드를 확장한 후
    expandExternal(z);

    // 순서 정렬을 실행
    upHeap(z);

    return true;

}

void expandExternal(Node * z) {

    Node * l = (Node *)malloc(sizeof(Node));
    Node * r = (Node *)malloc(sizeof(Node));

    l->parent = r->parent = z;
    l->key = r->key = -1;   // 내부 노드와 외부 노드를 구분하기 위해 key를 -1로 설정
    l->left = l->right = NULL;
    r->left = r->right = NULL;

    // 현재 노드의 좌, 우 자식을 초기화 (즉, 현재 노드의 외부 노드를 만든다.)
    z->left = l;
    z->right = r;

}

Node * advanceLast(Node * v) {


    // 현재 노드가 오른쪽 자식인 동안, 부모 노드로 이동
    while (!isRoot(v) && rightChild(parent(v)) == v) {
        v = parent(v);
    }
    
    // 현재 노드가 왼쪽 자식이면, 형제 노드로 이동
    if (!isRoot(v) && leftChild(parent(v)) == v) {
        v = rightChild(parent(v));
    }
    
    // 현재 노드가 내부 노드인 동안, 왼쪽 자식 이동
    while (leftChild(v) != NULL && rightChild(v) != NULL) {
        v = leftChild(v);
    }

    return v;

}

void upHeap(Node * v) {

    // 루트 노드가 아니고
    if (isRoot(v)) {
        return;
    }

    // 부모 노드보다 키갑이 큰 경우
    if (key(v) <= key(parent(v))) {
        return;
    }

    // 부모 노드와 교환 후 순서 정렬을 다시 수행
    swapElements(v, parent(v));
    upHeap(parent(v));

}

int removeMax(Heap * h) {

    int k;
    Node *w, *z;

    // 삭제할 키를 기록하고
    k = h->root->key;

    // 삭제할 키를 마지막 노드 키로 초기화를 진행 후
    w = h->last;
    h->root->key = w->key;

    // 마지막 노드의 위치를 초기화하고
    h->last = retreatLast(h->last);

    // 외부 노드로 삭제한 후
    z = leftChild(w);
    reduceExternal(z);

    // 순서 정렬 진행
    downHeap(h->root);

    return k;

}

Node * reduceExternal(Node * z) {

    Node * w = parent(z);
    Node * zs = sibling(z);

    if (isRoot(w)) {    // 루트노드이면 루트 노드를 초기화하고
        w = zs;
        zs->parent = NULL;
    } else {    // 그렇지 않으면
        Node *g = w->parent;    // 부모 노드가
        zs->parent = g;
        if (w == g->left) { // 왼쪽 자식이라면 왼쪽으로 이동
            g->left = zs;
        } else {    // 오른쪽 자식이라면 오른쪽으로 이동
            g->right = zs;
        }
    }

    free(z);
    free(w);

    return zs;

}

void downHeap(Node * v) {

    // 두 자식 모두 외부 노드 아닐 경우
    if (isExternal(leftChild(v)) && isExternal(rightChild(v))) {
        return;
    }

    // 자식중에 가장 큰 노드를 찾고
    Node * bigger = leftChild(v);
    if (isInternal(rightChild(v))) {
        if (key(rightChild(v)) > key(bigger)) {
            bigger = rightChild(v);
        }
    }

    // 자식 노드중에 자기 자신(v)보다 큰 노드(bigger)가 존재하다면
    if (key(v) >= key(bigger)) {
        return;
    }

    // 현재 노드(v)와 자식 중 가장 큰 노드(bigger)를 교환 후 순서 정렬을 다시 수행
    swapElements(v, bigger);
    downHeap(bigger);

}

Node * retreatLast(Node * v) {

    // 현재 노드가 왼쪽 자식인 동안, 부모 노드로 이동
    while (!isRoot(v) && leftChild(parent(v)) == v) {
        v = parent(v);
    }
    
    // 현재 노드가 오른쪽 자식이면, 형제 노드로 이동
    if (!isRoot(v) && rightChild(parent(v)) == v) {
        v = leftChild(parent(v));
    }
    
    // 현재 노드가 내부 노드인 동안, 오른쪽 자식 이동
    while (leftChild(v) != NULL && rightChild(v) != NULL && key(rightChild(v)) != -1) {
        v = rightChild(v);
    }

    return v;

}


void printHeap(Node * root) {

    if (root == NULL) {
        return;
    }

    Node *q[101];
    int front = 0, rear = 0;

    // 루트 노드를 큐에 삽입하고
    q[rear++] = root;

    while (front < rear) {  // 큐에 노드가 존재하면

        // 현재 큐에 있는 노드를 꺼내서 출력 후
        Node *c = q[front++ % 100];
        printf(" %d", key(c));

        // 좌,우 노드가 내부 노드일 경우 큐에 다시 삽입 후 다시 진행
        if (isInternal(leftChild(c))) {
            q[rear++ % 100] = leftChild(c);
        }
        
        if (isInternal(rightChild(c))){
            q[rear++ % 100] = rightChild(c);
        }
        
    }

}

void deleteNode(Node * root) {
    if (root != NULL) {
        deleteNode(leftChild(root));    // 좌측 노드 삭제
        deleteNode(rightChild(root));   // 우측 노드 삭제
        free(root);                     // 부모 노드 삭제
    }
}

void deleteHeap(Heap * h) {
    deleteNode(h->root);    // 노드 삭제
    free(h);    // 힙 삭제
}