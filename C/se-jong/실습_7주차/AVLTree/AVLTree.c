#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>

typedef struct __node {

	int key;
	int height;

	struct __node * parent;
	struct __node * lChild;
	struct __node * rChild;

} Node ;

typedef struct __avltree {

    Node * root;
    
} AvlTree;

AvlTree * initAVLTree() {

    AvlTree * avl = (AvlTree *)malloc(sizeof(AvlTree));
    avl->root = (Node *)malloc(sizeof(Node));
    avl->root->parent = avl->root->lChild = avl->root->rChild = NULL;

    return avl;

}

Node * parent(Node * w);
Node *sibling(Node *w);
Node *lChild(Node *w);
Node *rChild(Node *w);

bool isRoot(Node * w);
bool isExternal(Node *w);
bool isInternal(Node *w);

void expandExternal(Node *w);
Node *reduceExternal(AvlTree *avl, Node *z);

Node *treeSearch(Node *w, int k);
Node *inOrderSucc(Node *w);

bool updateHeight(Node *w);
bool isBalanced(Node *w);

void printTree(Node *w);
void freeTree(Node *w);

Node *restructure(AvlTree *avl, Node *x, Node *y, Node *z);

void searchAndFixAfterRemoval(AvlTree *avl, Node *w);
void searchAndFixAfterInsertion(AvlTree *avl, Node *w);

void insertItem(AvlTree *avl, int k);
int findElement(AvlTree *avl, int key);
int removeElement(AvlTree *avl, int k);

int main() {

	AvlTree *avl = initAVLTree();

	char oper;
	int key, result;

	while (true) {
        
        scanf("%c", &oper);
        getchar();

        switch (oper) {
        case 'i':
            scanf("%d", &key);
            insertItem(avl, key);
            getchar();
            break;
        case 'd':
            scanf("%d", &key);
            result = removeElement(avl, key);
            if (result == key) {
                printf("%d\n", result);
            }
            else {
                printf("X\n");
            }
            getchar();
            break;
        case 's':
            scanf("%d", &key);
            getchar();
            result = findElement(avl, key);
            if (result == -1) {
                printf("X\n");
            }
            else {
                printf("%d\n", result);
            }
            break;
        case 'p':
            printTree(avl->root);
            printf("\n");
            break;
        case 'q':
            freeTree(avl->root);
			free(avl);
			return 0;
		}

    }

}

Node * parent(Node * w) {

    if (w->parent != NULL) {
        return w->parent;
    }

    return NULL;
}

Node * sibling(Node *w) {

	if (w->parent == NULL) {
		return NULL;
	}

	if (w->parent->lChild == w) {
		return w->parent->rChild;
	}

	return w->parent->lChild;

}

Node * lChild(Node * w) {
    return w->lChild;
}

Node * rChild(Node * w) {
    return w->rChild;
} 

bool isRoot(Node * w) {

    if (w->parent == NULL) {
        return true;
    }

    return false;
    
}

bool isExternal(Node *w) {

	if ((lChild(w) == NULL) && (rChild(w) == NULL)) {
		return true;

	}

	return false;

}

bool isInternal(Node *w) {

	if ((lChild(w) != NULL) && (rChild(w) != NULL)) {
		return true;
	}

	return false;

}

void expandExternal(Node *w) {

	Node * l = (Node *)malloc(sizeof(Node));
	Node * r = (Node *)malloc(sizeof(Node));

	l->parent = w;
    l->height = 0;
    l->lChild = NULL;
    l->rChild = NULL;

	r->parent = w;
    r->height = 0;
    r->lChild = NULL;
    r->rChild = NULL;

    w->lChild = l;
    w->rChild = r;
    w->height = 1;

}

Node *reduceExternal(AvlTree *avl, Node * z) {

	Node *w, *zs, *g;

	w = z->parent;
	zs = sibling(z);

	if (w->parent == NULL) {
		avl->root = zs;
		zs->parent = NULL;
	} else {
		g = w->parent;
		zs->parent = g;

		if (w == g->lChild) {
			g->lChild = zs;
		} else {
			g->rChild = zs;
		}
	}

	free(z);
	free(w);

	w = z = NULL;
	return zs;

}

Node * treeSearch(Node *w, int k) {

	if (isExternal(w)) {
		return w;
	}
	
	if (w->key == k) {	// 현재 노드의 키와 k가 일치하면 종료 후
		return w;	// 현재 노드를 반납하고
	} else if (k <w->key) {	// k가 더 작으면
		return treeSearch(lChild(w), k);	// 좌측 노드를
	} else {	// 크다면
		return treeSearch(rChild(w), k);	// 우측 노드를 탐색
	}

}

Node * inOrderSucc(Node *w) {

	w = rChild(w);	// 오른쪽 자식 노드로 이동하고

	if (isExternal(w)) {
		return NULL;
	}

	while (isInternal(lChild(w))) {	// 현재 자식의 왼쪽 자식이 내부 노드라면
		w = lChild(w);	// 왼쪽 자식으로 이동
	}

	return w;	// 결과적으로 왼쪽 자식이 외부 노드인 노드 w를 반환활 것임

}

bool updateHeight(Node *w) {

	int lHeight = lChild(w)->height; 
    int rHeight = rChild(w)->height;

    int pHeight = (lHeight > rHeight ? lHeight : rHeight) + 1;

    if (w->height != pHeight) { // 부모의 높이와 자식의 높이가 다르다면
        w->height = pHeight;	// 자식 노드 중에 가장 큰 높이 + 1로 갱신
		return true;
	}

	return false;

}

bool isBalanced(Node *w) {

	int lHegiht = lChild(w)->height;
    int rHegiht = rChild(w)->height;
    int diff = abs(lHegiht - rHegiht);

    if (diff == 0 || diff == 1) {   // 좌우 노드 차이의 절대값이 0 or 1이라면
        return true;    // 균형
    }

    return false;   // 아니면 불균형

}

void printTree(Node *w) {

	if (isExternal(w)) {
		return;
	}

	printf(" %d", w->key);
	printTree(w->lChild);
	printTree(w->rChild);

}

void freeTree(Node *w) {

	if (isExternal(w)) {
		return;
	}
	
	freeTree(w->lChild); 
	freeTree(w->rChild);
	free(w);

}

Node * restructure(AvlTree * avl, Node *x, Node *y, Node *z) {

	Node *a, *b, *c;
	Node *T0, *T1, *T2, *T3;

	if ((z->key < y->key) && (y->key < x->key)) {	// RR 회전 

		a = z;
		b = y;	
		c = x;

		T0 = a->lChild;
		T1 = b->lChild;
		T2 = c->lChild;
		T3 = c->rChild;

	} else if (z->key > y->key && y->key > x->key) {	// LL 회전

		a = x;
		b = y;
		c = z;

		T0 = a->lChild;
		T1 = a->rChild;
		T2 = b->rChild;
		T3 = c->rChild;

	} else if (z->key < x->key && y->key > x->key) { // RL 회전

		a = z;
		b = x;
		c = y;

		T0 = a->lChild;
		T1 = b->lChild;
		T2 = b->rChild;
		T3 = c->rChild;

	} else {	// LR 회전

		a = y;
		b = x;
		c = z;

		T0 = a->lChild;
		T1 = b->lChild;
		T2 = b->rChild;
		T3 = c->rChild;

	}

	if (isRoot(z)) {
		avl->root = b;
		b->parent = NULL;
	} else {
        Node * g = parent(z);
        b->parent = g;

        if (lChild(g) == z) {
            g->lChild = b;
        } else {
            g->rChild = b;
        }
    }

	// 왼쪽 자식이 될 a 노드와 t0,t1 노드 연결 후 높이 갱신
	a->lChild = T0;
	T0->parent = a;

	a->rChild = T1;
	T1->parent = a;

	updateHeight(a);	

	// 오른쪽 자식이 될 c 노드와 t2,t3 노드 연결 후 높이 갱신
	c->lChild = T2;
	T2->parent = c;

	c->rChild = T3;
	T3->parent = c;

	updateHeight(c);

	// 부모 노드 b와 두 자식 노드(a,b)를 연결 후 높이 갱신
	b->lChild = a;
	a->parent = b;

	b->rChild = c;
	c->parent = b;

	updateHeight(b);

	return b;

}

void insertItem(AvlTree * avl, int k) {

	Node *w;
	
	w = treeSearch(avl->root, k);
	if (!isExternal(w)) {
		return;
	}

	w->key = k;
	expandExternal(w);
	searchAndFixAfterInsertion(avl, w);

}

int findElement(AvlTree * avl, int key) {

	Node *w;
	
	w = treeSearch(avl->root, key);
	if (isExternal(w)) {
        return -1;
    }

    return w->key;

}

int removeElement(AvlTree * avl, int k) {

	int removeData;
	Node *w, *z, *zs, *y;

	w = treeSearch(avl->root, k);
	if (isExternal(w)) {
		return -1;
	}

	removeData = w->key;

	z = lChild(w);
	if (!isExternal(z)) {
		z = rChild(w);
	}

	if (isExternal(z)) {	// 외부 노드라면 기존 방식대로 진행
		zs = reduceExternal(avl, z);
	} else {	// 외부 노드가 아니면
		y = inOrderSucc(w);	// 중위 순위 후계자(오른쪽 서브 트리 노드 중 가장 작은 키를 가지는 노드)를 찾은 후
		z = lChild(y);
		w->key = y->key;	// 삭제 할 노드와 중위 순위 후계자를 교환 후
		zs = reduceExternal(avl, z);	// 외부 노드를 제거
	}

	searchAndFixAfterRemoval(avl, parent(zs));	// 노드를 삭제한 후 높이 갱신 및 불균형 조사
	return removeData;
	
}

void searchAndFixAfterInsertion(AvlTree * avl, Node *w) {

	Node *z, *x, *y, *b;

	if (isRoot(w)) {
		return;
	}

	z = parent(w);
	while (updateHeight(z) && isBalanced(z)) {	// 루트 노드로 올라가면서 높이 갱신 및 불균형 조사
		if (isRoot(z)) {
			return;
		}
		z = parent(z);
	}

	if (isBalanced(z)) {	// 모든 노드에 대한 조사를 진행한 후 마지막 노드가 균형을 이룬다면 종료
		return;
	}

	// 그렇지 않다면
	if (lChild(z)->height > rChild(z)->height) {	// z의 자식 노드 중 높이가 높은 노드를 y로
		y = lChild(z);
	} else {
		y = rChild(z);
	}
	
	if (lChild(y)->height > rChild(y)->height) {	// y의 자식 노드 중 높이가 높은 노드를 x로
		x = lChild(y);
	} else {
		x = rChild(y);
	}

	// 설정하고 재구조화 진행
	b = restructure(avl, x, y, z);

}

void searchAndFixAfterRemoval(AvlTree * avl, Node *w) {

	Node *z, *y, *x, *b;

	if (w == NULL) {
		return;
	}

	z = w;
	while (updateHeight(z) && isBalanced(z)) {	// 루트 노드로 올라가면서 높이 갱신 및 불균형 조사
		if (isRoot(z)) {
			return;
		}
		z = parent(z);
	}

	if (isBalanced(z)) {	// 모든 노드에 대한 조사를 진행한 후 마지막 노드가 균형을 이룬다면 종료
		return;
	}

	if (lChild(z)->height > rChild(z)->height) {	// z의 자식 노드 중 높이가 높은 노드를 y로
		y = lChild(z);
	} else {
		y = rChild(z);
	}

	if (lChild(y)->height > rChild(y)->height) {	// y의 자식 노드 중 높이가 높은 노드를 x로
        x = lChild(y);
    } else if(lChild(y)->height < rChild(y)->height) {
        x = rChild(y);
    } else {	// 먄약 자식 노드의 높이가 같다면 단일 회전으로
		if (lChild(z) == y) { 
            x = lChild(y);
        } else {
            x = rChild(y);
        }
    }

	// 설정하고 재구조화 진행
	b = restructure(avl, x, y, z);

	// 재구조화를 진행한 후 해당 지점의 부모 노드에 대한 균형 조사를 다시 진행
	searchAndFixAfterRemoval(avl, parent(b));

}