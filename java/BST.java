import static java.lang.System.out;

/**
 * Implements a <i>Binary Search Tree</i> in symmetric order.
 * 
 * <h5>Lecture: Binary Search Trees (Week 4)</h5>
 * 
 * <p>
 *  A binary tree is either empty or two disjoint binary trees (left and right).
 *  Each node has a key, and every node's key is:
 * </p>
 * 
 * <ul>
 *   <li>Larger than all keys in its left subtree;</li>
 *   <li>Smaller than all keys in its right subtree;</li>
 * </ul>
 * 
 * @see RedBlackBST.java
 * @author eder.magalhaes
 * @param <Key> parameterized type for key.
 * @param <Value> parameterized type for value.
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;
    
    //linked list
    private class Node {
        private Key key;
        private Value val;
        
        private Node left, right;//left & right subtrees
        private int count = 1;
        
        public Node(Key k, Value v) {
            this.key = k;
            this.val = v;
        }
        
        @Override
        public String toString() {
            return ""+key;
        }
    }
    
    public boolean isEmpty() {
    	return size() == 0;
    }
    
    public int size() {
    	return size(root);
    }
    
    private int size(Node x) {
    	if (x == null)
    	    return 0;
    	return x.count;
    }
    
    public boolean contains(Key k) {
    	return get(k) != null;
    }
    
    public Value get(Key key) {
        Node x = root;
        
        while (x != null) {
            int cmp = key.compareTo(x.key);
            
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.val;
        }
        
        return null;
    }
    
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    
    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val);//se entrou aqui nao existe, e nao filhos
        
        int cmp = key.compareTo(x.key);
        
        if (cmp < 0)
            x.left = put(x.left, key, val); //put on left
        else if (cmp > 0)
            x.right = put(x.right, key, val); //put on right
        else
            x.val = val; //found node
        
        x.count = 1 + size(x.left) + size(x.right);
	return x;
    }
    
    public int height() {
        return height(root);
    }
    
    private int height(Node x) {
        if (x == null)
            return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }
    
    public Key min() {
        if (isEmpty())
            return null;
        return min(root).key;
    }
    
    private Node min(Node x) { 
        if (x.left == null)
            return x;
        return min(x.left);
    }
    
    public Key max() {
        if (isEmpty())
            return null;
        return max(root).key;
    }
    
    private Node max(Node x) { 
        if (x.right == null)
            return x;
        
        return max(x.right);
    }
    
    public Key select(int k) {
        if (k < 0 || k >= size())
            return null;
        
        Node x = select(root, k);
        return x.key;
    }
        
    private Node select(Node x, int k) {
        if (x == null)
            return null;
        
        int t = size(x.left);
        if (t > k)
            return select(x.left,  k);
        else if (t < k)
            return select(x.right, k-t-1);
        else
            return x;
    }
    
    public int rank(Key key) {
        return rank(key, root);
    }
    
    private int rank(Key key, Node x) {
        if (x == null)
            return 0;
        
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }
    
    public void deleteMin() {
        if (isEmpty())
            return;
        root = deleteMin(root);
    }
    
    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        
        x.left = deleteMin(x.left);
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    public void deleteMax() {
        if (isEmpty())
            return;
        root = deleteMax(root);
    }
    
    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        
        x.right = deleteMax(x.right);
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    public void delete(Key key) {
        root = delete(root, key);
    }
    
    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key); //delete left
        else if (cmp > 0)
            x.right = delete(x.right, key); //delete right
        else { 
            if (x.right == null)
                return x.left;
            
            if (x.left == null)
                return x.right;
            
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    //level order traversal
    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        inorder(root, q);
        return q;
    }
    
    private void inorder(Node x, Queue<Key> q) {
        if (x == null)
            return;
        
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }
    
    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<String, Integer>();
        
        bst.put("B", 1);
        bst.put("I", 1);
        bst.put("N", 1);
        bst.put("A", 1);
        bst.put("R", 1);
        bst.put("Y", 1);
        bst.put("S", 1);
        bst.put("E", 1);
        bst.put("A", 1);
        bst.put("R", 1);
        bst.put("C", 1);
        bst.put("H", 1);
        bst.put("T", 1);
        bst.put("R", 1);
        bst.put("E", 1);
        bst.put("E", 1);
        
        for (String k: bst.keys())
            out.printf("%s (key) %s (Value) %n", k, bst.get(k));
        
        out.printf("%nMax key: %s - Min key: %s%n", bst.max(), bst.min());
        out.printf("Height of tree is %s%n", bst.height());
    }
}
