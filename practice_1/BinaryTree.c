#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#pragma warning(disable:4996)

// 노드 및 트리 정의

typedef struct _node {
    int data;
    struct _node* left;
    struct _node* right;
} Node;

typedef struct _Btree
{
    Node * root;
    int size;
} BTree;

// 메소드 정의

void initBTree(BTree* bt);
Node * newNode(int data);
void makeBTree(BTree* bt, int data, int left, int right);
Node* searchroot(Node* root, int data);
void searchTree(Node* node, char* str);

int main(void)
{
    int num;
    int data, left, right;
    char str[101];

    BTree * bt; 
    bt = (BTree*)malloc(sizeof(BTree));
    initBTree(bt);

    scanf("%d", &num);
    for (int i = 0; i < num; i++)
    {
        scanf("%d %d %d", &data, &left, &right);
        makeBTree(bt, data, left, right);
    }

    scanf("%d", &num); getchar();
    for (int i = 0; i < num; i++)
    {
        scanf("%[^\n]", str); getchar();
        searchTree(bt->root, str);
    }

    return 0;
}


void initBTree(BTree* bt) {

    bt->root = NULL;
    bt->size = 0;

} // 트리 초기화

Node * newNode(int data) {
    
    Node* new = (Node*)malloc(sizeof(Node));

    new->data = data;
    new->left = new->right = NULL;

    return new;

} // 노드 생성


void makeBTree(BTree* bt, int data, int left, int right) {

    Node* node = NULL;
   
    if (!bt->root) { // 루트 노드가 없으면 바로 생성
        node = newNode(data);
        bt->root = node;
        bt->size++;
    } else {    // 루트 노드 존재시 해당 data를 가지는 노드 검색
        node = searchroot(bt->root, data);
    }
    
    if (left) { // 검색 한 루트 노드의 좌측 노드 생성
        node->left = newNode(left);
        bt->size++;
    } 

    if (right) { // 검색 한 루트 노드의 우측 노드 생성
        node->right = newNode(right);
        bt->size++;
    }

} // 트리 생성

Node* searchroot(Node* root, int data) {

    Node* node = NULL;

    // 루트 노드이면 반환
    if (root->data == data) {
        return root;
    }

    // 왼쪽 노드 탐색
    if (root->left && !node) {
            node = searchroot(root->left, data);
        }

     // 오른쪽 노드 탐색 (단, 왼쪽 노드 탐색 시 루트 노드를 발견하면 미실행)
     if (root->right && !node) {
            node = searchroot(root->right, data);
      }
  
    return node;

} // 루트 노드 찾기

void searchTree(Node* node, char* str) {
\
    int len = strlen(str);

    for (int i = 0; i <= len; i++) {
        printf(" %d", node->data);
        switch (*(str + i)) {
        case 'L':
            node = node->left;
            break;
        case 'R':
            node = node->right;
        }
    } printf("\n");
}