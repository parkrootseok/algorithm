#include <stdio.h>
#include <stdlib.h>
#pragma warning(disable:4996)

/* 노드 및 BST 정의 */
typedef struct _node {

	int key;
	struct _node* parent;
	struct _node* lChild;
	struct _node* rChild;

}node;

typedef struct _binarysearchTree {

	node* root;
	int size;
	int height;

} bstree;


/* 메소드 정의 */
node	*getNode();
bstree	*InitBinarySearchTree();
void	freeTree(node* root);

int		findElement(node* w, int key);
void	insertItem(node * w, int key);
int	removeElement(node* w, int key);

node	*treeSearch(node* w, int key);
void	expandExternal(node* n);
void	reduceExternal(node * root, node* n);
node	*sibling(node* n);
node	*inOrderSucc(node* w);

int		isExternal(node* w);
int		isInternal(node* w);

void inOrder(node* root);

int main() {

	bstree* bst;

	int key;
	char oper;

	bst = InitBinarySearchTree();

	while (1) {

		scanf("%c", &oper); getchar();	// 명령 입력

		switch (oper) {
		
		case 'i' :	// 삽입
			scanf("%d", &key); getchar();
			insertItem(bst->root, key);
			break;
		
		case 'd':	// 삭제
			scanf("%d", &key); getchar();
			if (key = removeElement(bst->root, key)) {
				printf("%d\n", key);
			}
			else {
				printf("X\n");
			}
			break;
		
		case 's':	// 탐색
			scanf("%d", &key); getchar();
			if (key = findElement(bst->root, key)) {
				printf("%d\n", key);
			}
			else {
				printf("X\n");
			}
			break;
		
		case 'p':	// 보기
			inOrder(bst->root);
			printf("\n");
			break;

		case 'q':	// 종료
			freeTree(bst->root);
			free(bst);
			return;
		}
	}
	
}

node	*getNode() {

	node* newNode = (node*)malloc(sizeof(node));

	newNode->parent = newNode->lChild = newNode->rChild = NULL;

	return newNode;

}

bstree	*InitBinarySearchTree() {

	bstree* bst = (bstree *)malloc(sizeof(bstree));

	bst->root = getNode();
	bst->size = 0;
	bst->height = 0;

	return bst;
}

void	freeTree(node* root) {

	if (isExternal(root)) {
		return;
	}

	free(root->lChild);
	free(root->rChild);
	free(root);

}

int	findElement(node* w, int key) {

	w = treeSearch(w, key);

	if (isExternal(w)) {
		return 0;
	} else {
		return w->key;
	}
	return;

}

void	insertItem(node* w, int key) {

	node *cur;

	cur = treeSearch(w, key);

	if (isInternal(cur)) return;
	
	else {
		cur->key = key;
		expandExternal(cur);
	}

}

int	removeElement(node* root, int key) {

	node *w, *z, *y;
	int k;

	w = treeSearch(root, key);		// key를 가지는 노드 탐색

	if (isExternal(w)) {			// 탐색 결과가 외부 노드일 경우 존재 하지 않음을 의미.
		return 0;
	}

	k = w->key;						// 삭제할 key 저장
	z = w->lChild;				
	if (!isExternal(z)) {			// 왼쪽 자식이 외부 노드가 아니면
		z = w->rChild;				// 오른쪽 노드 조사
	}
	if (isExternal(z)) {			// 오른쪽 자식이 외부 노드이면(즉, 한쪽만 외부 노드인 경우를 의미)
		reduceExternal(w, z);	// 오른쪽 자식 노드를 삭제 노드와 교환
	} else {
		y = inOrderSucc(w);
		z = y->lChild;
		w->key = y->key;
		reduceExternal(w, z);
	} 

	return k;
}

node	*treeSearch(node* w, int key) {

	if (isExternal(w)) {
		return  w;
	}

	if (key == w->key) {
		return w;
	}
	else if (key < w->key) {
		return treeSearch(w->lChild, key);
	}
	else {
		return treeSearch(w->rChild, key);
	}

}

void	 expandExternal(node* n) {

	n->lChild = getNode(); n->lChild->parent = n;
	n->rChild = getNode(); n->rChild->parent = n;

}

void	reduceExternal(node * root, node* z) {
	
	node *w, *zs, *g;

	w = z->parent;
	zs = sibling(z);
	if (w->parent == NULL) {
		
		root->lChild = zs->lChild; root->lChild->parent = zs;
		root->rChild = zs->rChild; root->rChild->parent = zs;
		zs->parent = NULL;

	}
	else {
		g = w->parent;
		zs->parent = g;
		if (w == g->lChild) {
			g->lChild = zs;
		}
		else {
			g->rChild = zs;
		}
	}

	free(z);
	free(w);
	
	return zs;
}

node	*sibling(node* n) {

	if (n->parent == NULL) {
		return;
	}

	if (n == n->parent->lChild) // 자신과 왼쪽 자식이 같으면
		return n->parent->rChild; // 오른쪽이 형제
	else 
		return n->parent->lChild; // 다르면 왼쪽이 형제

}

node* inOrderSucc(node* w) {

	w = w->rChild;
	if (isExternal(w)) return;
	
	while (isInternal(w->lChild)){
		w = w->lChild;
	}

	return w;

}

int		isExternal(node* w) {

	if (w->lChild == NULL && w->rChild == NULL) return 1;
	else return 0;

}

int		isInternal(node* w) {

	if (w->lChild != NULL || w->rChild != NULL) return 1;
	else return 0;
	
}

void	inOrder(node * root) {

	if (isExternal(root)) return;
	
	printf(" %d", root->key);
	inOrder(root->lChild);
	inOrder(root->rChild);

}
