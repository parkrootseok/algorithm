import java.io.*;
import java.util.*;

/**
 * BOJ_이진검색트리
 * @author parkrootseok
 */
public class Main {

	public static class Node {

		int value;
		Node parent;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
		}

	}

	public static class BinarySearchTree {

		Node root;

		public void add(int value) {

			if (Objects.isNull(root)) {
				root = new Node(value);
				return;
			}
			
			Node cNode = root;
			while (true) {

				if (value < cNode.value) {

					if (Objects.isNull(cNode.left)) {
						cNode.left = new Node(value);
						cNode.left.parent = cNode;
						break;
					}

					cNode = cNode.left;

				} else {

					if (Objects.isNull(cNode.right)) {
						cNode.right = new Node(value);
						cNode.right.parent = cNode;
						break;
					}

					cNode = cNode.right;

				}

			}

		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static StringBuilder sb;
	static BinarySearchTree binarySearchTree;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		binarySearchTree = new BinarySearchTree();
		while (true) {

			String input = br.readLine();

			if (Objects.isNull(input)) {
				break;
			}

			int value = Integer.parseInt(input);
			binarySearchTree.add(value);

		}

		postOrderTraverse(binarySearchTree.root);
		bw.write(sb.toString());
		bw.close();

	}

	public static void postOrderTraverse(Node parent) {

		if (Objects.isNull(parent)) {
			return;
		}

		postOrderTraverse(parent.left);
		postOrderTraverse(parent.right);
		sb.append(parent.value).append("\n");

	}

}