package problemset1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node {
	Integer value;
	Node left;
	Node right;
	public Node(Integer input) {
		this.value = input;
		this.left = null;
		this.right = null;
	}
}

class NodeTree {
	Node root;
	
	public NodeTree() {
		this.root = null;
	}
	
	public NodeTree(Integer value) {
		this.root = new Node(value);
	}
	
	public void addNode(Integer value) {
		this.root = addNodeRecursively(this.root, value);
	}
	
	public void deleteNode(Integer value) {
		this.root = deleteRecursively(this.root, value);
	}
	
	private Node deleteRecursively(Node root2, Integer value) {
		
		if(root2 != null) {
			if(root2.value == value) { 
				if(root2.left == null && root2.right == null) {
					root2 = null;
					return root2;
				} else if(root2.left ==  null) {
					root2 = root2.right;
				} else if(root2.right == null) {
					root2 = root2.left;
				} else {
					Node smallest = findSmallestRecursively(root2.right);
					root2.value = smallest.value;
					root2.right = deleteRecursively(root2.right, smallest.value);
				}
			} else {
				if(value < root2.value) { 
					root2.left = deleteRecursively(root2.left, value);
				} else { 
					root2.right = deleteRecursively(root2.right, value);
				}
			}
		}
		
		return root2;
	}

	private Node findSmallestRecursively(Node right) {
		return right.left == null ? right : findSmallestRecursively(right.left);
	}

	public Node searchNode(Integer value) {
		return searchRecursively(this.root, value);
	}
	
	private Node searchRecursively(Node root2, Integer value) {
		Node found = null;
		if(root2 != null) {
			if(root2.value == value) {
				return root2;
			} else if(value < root2.value) {
				found = searchRecursively(root2.left, value);
			} else {
				found = searchRecursively(root2.right, value);
			}
		}
		return found;
	}

	public Node getRoot() {
		return root;
	}
	
	public void traverse() {
		traverseRecursively(this.root);
	}
	
	private void traverseRecursively(Node root2) {
		if(root2 != null) {
			
			traverseRecursively(root2.left);
			System.out.println(root2.value);
			traverseRecursively(root2.right);
		}
	}

	public Node addNodeRecursively(Node currentNode, Integer value) {
		
		if(currentNode == null) {
			currentNode = new Node(value);
		} else {
			if(value < currentNode.value) {
				currentNode.left = addNodeRecursively(currentNode.left, value);
			} else if(value > currentNode.value) {
				currentNode.right = addNodeRecursively(currentNode.right, value);
			}
		}
		return currentNode;
	}
	
	public void levelOfOrderTraverse() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(this.root);
		traverseLORRecursively(queue);
		
	}
	
	private void traverseLORRecursively(Queue<Node> queue) {
		if(!queue.isEmpty()) {
			Node removed = queue.remove();
			System.out.print(removed.value + " ");
			if(removed.left != null) {
				queue.add(removed.left);
			}
			if(removed.right != null) {
				queue.add(removed.right);
			}
			traverseLORRecursively(queue);
		}
	}

	public void levelOfOrderTraverse2() {
		ArrayList<Node> stack = new ArrayList<Node>();
		stack.add(this.root);
		traverseLORRecursively2(stack);
		
	}

	private void traverseLORRecursively2(ArrayList<Node> stack) {
		ArrayList<Node> tempStack = new ArrayList<Node>();
		stack.forEach(node -> {
			System.out.print(node.value + " ");
			if(node.left != null) {
				tempStack.add(node.left);
			}
			if(node.right != null) {
				tempStack.add(node.right);
			}
		});
		System.out.println();
		if(tempStack.size() > 0) {
			traverseLORRecursively2(tempStack);
		}
	}

	public void checkIfBST() {
		
		traverseInOrderDFSCheckOrder(this.root, null);
	}
	
	private Integer traverseInOrderDFSCheckOrder(Node current, Integer previousValue) {
		
		if(current != null) {
			previousValue = traverseInOrderDFSCheckOrder(current.left, previousValue);
			if(previousValue != null && current.value < previousValue) {
				System.out.println("NOT BST");
			}
			previousValue = traverseInOrderDFSCheckOrder(current.right, current.value);
		}
		
		
		return previousValue;
	}
}

public class Datastructures {

	public static void main(String[] args) {
		NodeTree tree = new NodeTree();
		tree.addNode(8);
		tree.addNode(3);
		tree.addNode(5);
		tree.addNode(2);
		tree.addNode(4);
		tree.addNode(9);
		tree.addNode(6);
		tree.addNode(7);
		tree.addNode(1);

		tree.traverse();
		
		tree.levelOfOrderTraverse();
		System.out.println();
		tree.levelOfOrderTraverse2();
		System.out.println();
		System.out.println(tree.searchNode(0) != null);
		
		tree.deleteNode(5);
		System.out.println(tree.searchNode(5) != null);
		tree.levelOfOrderTraverse();
		
		System.out.println();
		tree.searchNode(4).value = 3;
		tree.checkIfBST();
	}

}
