package org.example;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    int height;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}

class BinaryTree {
    TreeNode root;

    private int height(TreeNode node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private void updateHeight(TreeNode node) {
        if (node != null)
            node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private int getBalance(TreeNode node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        } else {
            return root;
        }

        updateHeight(root);

        int balance = getBalance(root);

        if (balance > 1 && value < root.left.value) {
            return rightRotate(root);
        }
        if (balance < -1 && value > root.right.value) {
            return leftRotate(root);
        }
        if (balance > 1 && value > root.left.value) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && value < root.right.value) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }



    public void postorderTraversal(TreeNode node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.value + " ");
        }
    }

    public void postorderTraversal() {
        postorderTraversal(root);
    }
}

public class Main {
    public static void main(String[] args) {
        int[] elements = {45, 27, 67, 36, 56, 15, 75, 31, 53, 39, 64};

        BinaryTree bst = new BinaryTree();
        for (int element : elements) {
            bst.insert(element);
        }

        System.out.println("Postorder Traversal:");
        bst.postorderTraversal();

        int[] unbalancedElements = {10, 5, 20, 15, 25};
        for (int element : unbalancedElements) {
            bst.insert(element);
        }

        System.out.println("\n\nAfter Adding Unbalanced Elements:");
        System.out.println("Postorder Traversal:");
        bst.postorderTraversal();
    }
}
