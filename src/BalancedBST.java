import java.util.*;

class BSTNode {
    public int NodeKey;
    public BSTNode Parent;
    public BSTNode LeftChild;
    public BSTNode RightChild;
    public int Level;

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root;

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        Arrays.sort(a);

        Root = GenerateTreeRec(a, 0, a.length - 1, null, 0);
    }

    private BSTNode GenerateTreeRec(int[] a, int start, int end, BSTNode parent, int level) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        BSTNode node = new BSTNode(a[mid], parent);
        node.Level = level;

        node.LeftChild = GenerateTreeRec(a, start, mid - 1, node, level + 1);
        node.RightChild = GenerateTreeRec(a, mid + 1, end, node, level + 1);

        return node;
    }

    public boolean IsBalanced(BSTNode node) {
        if (node == null) {
            return true;
        }

        int leftDepth = GetDepth(node.LeftChild);
        int rightDepth = GetDepth(node.RightChild);

        if (Math.abs(leftDepth - rightDepth) <= 1 &&
                IsBalanced(node.LeftChild) &&
                IsBalanced(node.RightChild)) {
            return true;
        }

        return false;
    }

    private int GetDepth(BSTNode node) {
        if (node == null) {
            return 0;
        }

        return node.Level;
    }
}