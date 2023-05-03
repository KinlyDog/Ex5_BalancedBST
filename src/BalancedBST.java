import java.util.*;

class BSTNode {
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок
    public int Level; // глубина узла

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
        Level = 0;
    }
}

class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        Arrays.sort(a);
        Root = GenerateTreeRec(a, 0, a.length - 1, null, 0);
    }

    private BSTNode GenerateTreeRec(int[] a, int startIndex, int endIndex, BSTNode parent, int level) {
        if (startIndex > endIndex) {
            return null;
        }

        int mid = startIndex + (endIndex - startIndex) / 2;
        BSTNode node = new BSTNode(a[mid], parent);
        node.Level = level;

        node.LeftChild = GenerateTreeRec(a, startIndex, mid - 1, node, level + 1);
        node.RightChild = GenerateTreeRec(a, mid + 1, endIndex, node, level + 1);

        return node;
    }

    public boolean IsBalanced(BSTNode root_node) {
        if (root_node == null) {
            return true;
        }

        int leftDepth = getDepth(root_node.LeftChild);
        int rightDepth = getDepth(root_node.RightChild);

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }

        return IsBalanced(root_node.LeftChild) && IsBalanced(root_node.RightChild);
    }

    private int getDepth(BSTNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = getDepth(node.LeftChild);
        int rightDepth = getDepth(node.RightChild);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}