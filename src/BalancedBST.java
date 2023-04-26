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

        Root = GenerateTreeRec(a, 0, a.length - 1, null);
    }

    private BSTNode GenerateTreeRec(int[] a, int start, int end, BSTNode parent) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        BSTNode node = new BSTNode(a[mid], parent);

        node.LeftChild = GenerateTreeRec(a, start, mid - 1, node);
        node.RightChild = GenerateTreeRec(a, mid + 1, end, node);

        return node;
    }

    public boolean IsBalanced(BSTNode node) {
        if (node == null) {
            return true;
        }

        if (Math.abs(GetTreeHeight(node.LeftChild) - GetTreeHeight(node.RightChild)) > 1) {
            return false;
        }

        return IsBalanced(node.LeftChild) && IsBalanced(node.RightChild);
    }

    private int GetTreeHeight(BSTNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(GetTreeHeight(node.LeftChild), GetTreeHeight(node.RightChild)) + 1;
    }

}  