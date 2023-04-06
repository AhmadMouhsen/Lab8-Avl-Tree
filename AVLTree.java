package student;

import java.util.*;

public class AVLTree<T extends Comparable<T>> {
    //Fields
    private Node root;
    
    
    //Node class
    private class Node {
        int data;
        Node left;
        Node right;
        int height;
        int balance;
        
        public Node(int data) {
            this.data = data;
            this.height = 1;
            this.left = null;
            this.right = null;
            this.balance = 0;
        }
    }
    
    //Default constructor
    public AVLTree() {
        root = null;
    }
    
    
    //Insert a new value into tree
    public void add(int data) {
        root = add(root, data);
    }
    
    //recursive method
    private Node add(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        
        //left is less, right if larger, else it already exists.
        //recursive
        if (data > node.data) {
            node.left = add(node.left, data);
        } else if (data < node.data) {
            node.right = add(node.right, data);
        } else {
            return node;
        }
        
        //Update the height of the current node
        //If node data is null then height is 0.
        node.height = 1 + Math.max(height(node.left), height(node.right));
        
        //Calculate the balance factor of the current node
        //Positive is left node height more.
        //Negative is right node height more.
        node.balance = getBalance(node);
        
        //If the node is unbalanced then balance.
        if (node.balance > 1) {
            if (data > node.left.data) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (node.balance < -1) {
            if (data < node.right.data) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        
        return node;
    }
    
    //Get the height of the node
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
    //Get the balance factor of a node.
    //Positive is left node height more.
    //Negative is right node height more.
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }
    
    //Rotate a node to the right
    private Node rightRotate(Node node) {
        Node left = node.left;
        Node right = left.right;
        
        left.right = node;
        node.left = right;
        
        node.height = 1 + Math.max(height(node.left), height(node.right));
        left.height = 1 + Math.max(height(left.left), height(left.right));
        
        return left;
    }
    
    //Rotate a node to the left
    private Node leftRotate(Node node) {
        Node right = node.right;
        Node left = right.left;
        
        right.left = node;
        node.right = left;
        
        node.height = 1 + Math.max(height(node.left), height(node.right));
        right.height = 1 + Math.max(height(right.left), height(right.right));
        
        return right;
    }
    
    //Print the AVL tree by level 
    public void printTree() {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            ArrayList<Node> nextNodes = new ArrayList<Node>();
            for (Node node : nodes) {
                if (node.right != null) {
                    nextNodes.add(node.right);
                }
                if (node.left != null) {
                    nextNodes.add(node.left);
                }
                System.out.print(node.data + " ");
            }
            System.out.println();
            nodes = nextNodes;
        }
    }
    
    public static void main(String args[]) {
        AVLTree rightTree = new AVLTree();
        System.out.println("right rotation before adding 3");
        rightTree.add(5);
        rightTree.add(4);
        rightTree.printTree();
        System.out.println("right rotation after adding 3");
        rightTree.add(3);
        rightTree.printTree();
        System.out.println();
        
        
        AVLTree leftTree = new AVLTree();
        System.out.println("left rotation before: 7");
        leftTree.add(5);
        leftTree.add(6);
        leftTree.printTree();
        System.out.println("left rotation after: 7");
        leftTree.add(7);
        leftTree.printTree();
        System.out.println();
        
        AVLTree rightLeftTree = new AVLTree();
        System.out.println("right-left rotation before: 14");
        rightLeftTree.add(10);
        rightLeftTree.add(4);
        rightLeftTree.add(16);
        rightLeftTree.add(17);
        rightLeftTree.add(15);
        rightLeftTree.printTree();
        System.out.println("right-left rotation after: 14");
        rightLeftTree.add(14);
        rightLeftTree.printTree();
        System.out.println();
        
        AVLTree leftRightTree = new AVLTree();
        System.out.println("left-right rotation before: 9");
        leftRightTree.add(10);
        leftRightTree.add(11);
        leftRightTree.add(7);
        leftRightTree.add(8);
        leftRightTree.add(6);
        leftRightTree.printTree();
        System.out.println("left-right rotation after: 9");
        leftRightTree.add(9);
        leftRightTree.printTree();
        
        
    }
    
}


