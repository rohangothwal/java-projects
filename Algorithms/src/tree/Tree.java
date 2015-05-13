package tree;
import java.io.*;
import java.util.*;
import java.lang.Math;

public class Tree {
	class TreeNode {
		TreeNode left;
		int data;
		TreeNode right;

		public TreeNode (int d) {
			data=d;
			left=right=null;
		}

		public void insert(int d){
			if (d <data){
				if (left == null)
					left = new TreeNode(d);
				else
					left.insert(d);
			}
			else
				if (d > data) {
					if (right == null)
						right= new TreeNode(d);
					else
						right.insert(d);
				}
		}
	}

	/* Now that a TreeNode is defined, we are going to
	   build a binary tree.

	   											*/
		private TreeNode root;

		/* Construct an empty binary tree at the root
												*/
		public Tree () {root=null;}

		/* Insert a new node in the binary tree. If the
		root node is null, create the root node now.
												*/
       	 public void insertNode (int d){
			if (root == null)
				root = new TreeNode(d);
			else
				root.insert(d);
		}

		private void inOrderDriver(TreeNode node){
			if (node == null)
				return;
			else {
				inOrderDriver(node.left);
				System.out.print(" "+node.data+" -> ");
				inOrderDriver(node.right);
			}
		}


		public void inOrderTraversal(){inOrderDriver(root);}


		public int treeSize() {int k=treeSizeDriver(root); return k;}
		public int treeHeight() {int k=treeHeightDriver(root);
						return k;}
		public int leafCount() {int k=leafCountDriver(root);
						return k;}

		    // count the number of treenodes recursively

		private int treeSizeDriver(TreeNode r) {
			if(r == null)
				return 0;
			else
				return(1 + treeSizeDriver(r.left)+
					treeSizeDriver(r.right));
		}

		// Compute the height of the tree recursively

		private int treeHeightDriver(TreeNode r) {
			if (r == null)
				return 0;
			else
				return Math.max(treeHeightDriver(r.left),
					treeHeightDriver(r.right))+1;
		}

		// Count the leaves of a tree recursively

		




private int leafCountDriver (TreeNode r) {
			if (r == null)
				return 0;
			else
				if (r.right==null && r.left==null)
					return 1;
				else
					return leafCountDriver(r.left) +
						   leafCountDriver(r.right);
		}

		public static void main(String args []){
			Tree thisTree = new Tree();
			int i, j, k;
			for (i=0; i<10; i++){
				j=(int)(100*Math.random());
				System.out.print(j+" ");
				thisTree.insertNode(j);
			}
			System.out.println("\n\n");
			System.out.println("Height of this tree: "+
						thisTree.treeHeight()+'\n');

			System.out.println("Node count in this tree: "+
						thisTree.treeSize()+'\n');

			System.out.println("Leaf count of this tree: "+
						thisTree.leafCount()+'\n');

			// Traversal routine
			System.out.println("Inorder traversal profile: ");
			System.out.println(" ");
			thisTree.inOrderTraversal();
			System.out.println(" ");
			System.out.println("\n *****************\n");
			System.out.println("\n");
		}
	}
