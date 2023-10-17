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

Node *sibling(Node *w);
Node *lChild(Node *w);
Node *rChild(Node *w);

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
            return 0;
        }

    }

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

bool isExternal(Node *w) {

	if ((w->lChild == NULL) && (w->rChild == NULL)) {
		return true;

	}

	return false;

}

bool isInternal(Node *w) {

	if ((w->lChild != NULL) && (w->rChild != NULL)) {
		return true;
	}

	return false;

}

void expandExternal(Node *w) {

	Node * left = (Node *)malloc(sizeof(Node));
	Node * right = (Node *)malloc(sizeof(Node));

	left->height = 0;
	left->parent = w;
	left->lChild = NULL;
	left->rChild = NULL;
	
	right->height = 0;
	right->parent = w;
	right->lChild = NULL;
	right->rChild = NULL;

	w->lChild = left;
	w->rChild = right;
	w->height = 1;

	return;

}

Node *reduceExternal(AvlTree *avl, Node * z) {

	Node *w, *zs, *g;

	w = z->parent;
	zs = sibling(z);

	if (w->parent == NULL) {
		avl->root = zs;
		zs->parent = NULL;
	}

	else {

		g = w->parent;
		zs->parent = g;

		if (w == g->lChild) {
			g->lChild = zs;
		}
		else if (w == g->rChild) {
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
	
	if (w->key == k)
		return w;
	else if (w->key > k) {
		return treeSearch(w->lChild, k);
	}
	else {
		return treeSearch(w->rChild, k);
	}
}

Node * inOrderSucc(Node *w) {

	w = w->rChild;

	if (isExternal(w)) {
		return NULL;
	}

	while (isInternal(w->lChild)) {
		w = w->lChild;
	}

	return w;

}

bool updateHeight(Node *w) {

	int lHeight = w->lChild->height; 
    int rHeight = w->rChild->height;

    int pHeight = (lHeight > rHeight ? lHeight : rHeight) + 1;

    if (w->height != pHeight) {
        w->height = pHeight;
        return true;
    }

    return false;

}

bool isBalanced(Node *w) {

	int lHegiht = w->lChild->height;
    int rHegiht = w->rChild->height;

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

	else {
		printf(" %d", w->key);
		printTree(w->lChild);
		printTree(w->rChild);
	}

}

void freeTree(Node *w) {

	if (isExternal(w)) {
		return;
	}

	else {
		freeTree(w->lChild);
		freeTree(w->rChild);
		free(w);
	}

}

Node * restructure(AvlTree * avl, Node *x, Node *y, Node *z) {

	Node *a, *b, *c;
	Node *T0, *T1, *T2, *T3;

	if ((z->key < y->key) && (y->key < x->key)) {	// LL 회전

		a = z;
		b = y;
		c = x;

		T0 = a->lChild;
		T1 = b->lChild;
		T2 = c->lChild;
		T3 = c->rChild;

	} else if (z->key > y->key && y->key > x->key) {	// RR 회전

		a = x;
		b = y;
		c = z;

		T0 = a->lChild;
		T1 = a->rChild;
		T2 = b->rChild;
		T3 = c->rChild;

	}

	else if (z->key < x->key && y->key > x->key) { // RL 회전

		a = z;
		b = x;
		c = y;

		T0 = a->lChild;
		T1 = b->lChild;
		T2 = b->rChild;
		T3 = c->rChild;

	} else {

		a = y;
		b = x;
		c = z;

		T0 = a->lChild;
		T1 = b->lChild;
		T2 = b->rChild;
		T3 = c->rChild;

	}

	if (z->parent == NULL) {
		avl->root = b;
		b->parent = NULL;
	} else if (z->parent->lChild == z) {
		z->parent->lChild = b;
		b->parent = z->parent;
	} else if (z->parent->rChild == z) {
		z->parent->rChild = b;
		b->parent = z->parent;
	}



	a->lChild = T0;
	T0->parent = a;

	a->rChild = T1;
	T1->parent = a;

	updateHeight(a);

	c->lChild = T2;
	T2->parent = c;

	c->rChild = T3;
	T3->parent = c;

	updateHeight(c);

	b->lChild = a;
	a->parent = b;

	b->rChild = c;
	c->parent = b;

	updateHeight(b);

	return b;

}

void insertItem(AvlTree * avl, int k) {
	
	Node *w = treeSearch(avl->root, k);

	if (!isExternal(w)) {
		return;
	}

	w->key = k;
	expandExternal(w);
	searchAndFixAfterInsertion(avl, w);

}

int findElement(AvlTree * avl, int key) {

    Node *w = treeSearch(avl->root, key);

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

	if (isExternal(z)) {
		zs = reduceExternal(avl, z);
	} else {

		y = inOrderSucc(w);
		z = lChild(y);
		w->key = y->key;
		zs = reduceExternal(avl, z);

	}

	searchAndFixAfterRemoval(avl, zs->parent);
	return removeData;
	
}

void searchAndFixAfterInsertion(AvlTree * avl, Node *w) {

	Node *x, *y, *z, *b;

	if (w->parent == NULL) {
		return;
	}

	z = w->parent;
	while (updateHeight(z) && isBalanced(z)) {
		if (z->parent == NULL) {
			return;
		}
		z = z->parent;
	}

	if (isBalanced(z)) {
		return;
	}

	if (lChild(z)->height > rChild(z)->height) {
		y = lChild(z);
	} else {
		y = rChild(z);
	}
	
	if (lChild(y)->height > rChild(y)->height) {
		x = lChild(y);
	} else {
		x = rChild(y);
	}

	restructure(avl, x, y, z);

}

void searchAndFixAfterRemoval(AvlTree * avl, Node *w) {

	Node *x, *y, *z, *b;

	if (w == NULL) {
		return;
	}

	z = w;

	while (updateHeight(z) && isBalanced(z)) {
		if (z->parent == NULL) {
			return;
		}
		z = z->parent;
	}

	if (isBalanced(z)) {
		return;
	}

	if (lChild(z)->height > rChild(z)->height) {
		y = lChild(z);
	} else {
		y = rChild(z);
	}

	if (lChild(y)->height > rChild(y)->height) {
        x = lChild(y);
    } else if (lChild(y)->height < rChild(y)->height) {
        x = rChild(y);
    } else {
		if (lChild(z) == y) {
            x = lChild(y);
        }
        else if (rChild(z) == y) {
            x = rChild(y);
        }
    }

	b = restructure(avl, x, y, z);
	searchAndFixAfterRemoval(avl, b->parent);

}

