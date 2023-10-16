#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

typedef struct __node {
    
    int key;
    struct __node * parent;
    struct __node * lChild;
    struct __node * rChild;
    
} Node;

typedef struct __binarytree {

    Node * root;
    
} BinaryTree;

BinaryTree * initBinaryTree() {

    BinaryTree * bst = (BinaryTree *)malloc(sizeof(BinaryTree));
    bst->root = (Node *)malloc(sizeof(Node));

    return bst;

}

Node * makeNode() {

    Node *newNode = (Node *)malloc(sizeof(Node));
    newNode->key = -1;
    newNode->parent = newNode->lChild = newNode->rChild = NULL;

    return newNode;

}

Node * parent(Node *v) {
    return v->parent;
}

bool isRoot(Node * w) {

    if (parent(w) == NULL) {
        return true;
    }

    return false;
}


Node *leftChild(Node * v) {
    return v->lChild;
}

Node *rightChild(Node *v) {
    return v->rChild;
}

Node * sibling(Node * w) {
    
    Node *p = parent(w);

    if (leftChild(p) == w) {
        return rightChild(p);
    }

    return leftChild(p);
}


void expandExternal(Node * w) {

    Node *lChild = makeNode();
    Node *rChild = makeNode();

    lChild->parent = rChild->parent = w;
    lChild->lChild = lChild->rChild = NULL;
    rChild->lChild = rChild->rChild = NULL;

    w->lChild = lChild;
    w->rChild = rChild;

}

Node * reduceExternal(BinaryTree * bst, Node * z) {

    Node * w = parent(z);
    Node * zs = sibling(z);

    if (isRoot(w)) {
        bst->root = zs;
    } else {

        Node *g = parent(w);
        zs->parent = g;

        if (leftChild(g) == w) {
            g->lChild = zs;
        }
        else {
            g->rChild = zs;
        }
    }

    free(w);
    free(z);

    return zs;

}

void insertItem(BinaryTree * bst, int key);
int findElement(BinaryTree *bst, int key);
int removeElement(BinaryTree * bst, int key);
Node * treeSearch(Node * w, int key);

bool isExternal(Node * w) {

    if (leftChild(w) == NULL && rightChild(w) == NULL) {
        return true;
    }

    return false;

}

bool isInternal(Node * w) {

    if (leftChild(w) != NULL && rightChild(w) != NULL) {
        return true;
    }

    return false;

}

Node * inOrderSucc(Node * w);
void print(BinaryTree *bst);
void preorderTraverse(Node *root);

int main() {

    BinaryTree *bst = initBinaryTree();

    char oper;
    int key, result;
    
    while (true) {
        
        scanf("%c", &oper);
        getchar();

        switch (oper) {
        case 'i':
            scanf("%d", &key);
            getchar();
            insertItem(bst, key);
            break;
        case 'd':
            scanf("%d", &key);
            getchar();
            result = removeElement(bst, key);
            if (result == -1) {
                printf("X\n");
            }
            else {
                printf("%d\n", result);
            }
            break;
        case 's':
            scanf("%d", &key);
            getchar();
            result = findElement(bst, key);
            if (result == -1) {
                printf("X\n");
            }
            else {
                printf("%d\n", result);
            }
            break;
        case 'p':
            print(bst);
            break;
        case 'q':
            return 0;
        }

    }

}

Node * treeSearch(Node * w, int key) {

    if (isExternal(w)) {
        return w;
    }

    if (w->key == key) {
        return w;
    } else if (w->key > key) {
        return treeSearch(leftChild(w), key);
    } else {
        return treeSearch(rightChild(w), key);
    }

}

void insertItem(BinaryTree * bst, int key) {

    Node *w = treeSearch(bst->root, key);

    if (isInternal(w)) {
        return;
    }

    w->key = key;
    expandExternal(w);

}

int findElement(BinaryTree * bst, int key) {

    Node * w = treeSearch(bst->root, key);    
    return w->key;
    
}

int removeElement(BinaryTree * bst, int key) {

    int removeData;
    Node *w, *z, *zs, * y;

    // 삭제할 노드 검색
    w = treeSearch(bst->root, key);

    // 삭제할 노드가 외부 노드라면 예외 처리
    if (isExternal(w)) {
        return -1;
    }

    removeData = w->key;
    
    // 외부 노드인 노드를 탐색
    z = leftChild(w);
    if (!isExternal(z)) {
        z = rightChild(w);
    }

    // 탐색한 노드가 외부 노드라면 w, z를 삭제
    if (isExternal(z)) {
        reduceExternal(bst, z);
    } else {                       // 두 자식 노드가 모두 외부 노드가 아니라면
        y = inOrderSucc(w); // 중위순위후계자를 검색
        z = leftChild(y);
        w->key = y->key;
        reduceExternal(bst, z);
    }

    return removeData;

}

Node * inOrderSucc(Node * w) {

    w = rightChild(w);

    if(isExternal(w)) {
        return NULL;
    }

    while (isInternal(leftChild(w))) {
        w = leftChild(w);
    }

    return w;

}

void print(BinaryTree *bst) {
    preorderTraverse(bst->root);
    printf("\n");
}

void preorderTraverse(Node *root) {

    if (isInternal(root)) {
        printf(" %d", root->key);
        preorderTraverse(root->lChild);
        preorderTraverse(root->rChild);
    }
}