import static java.lang.System.out;


/**
 * Implements a <i>red-black Binary Search Tree</i>, maintains symmetric order 
 * and perfect black balance.
 * 
 * <h5>Lecture: Binary Search Trees (Week 5)</h5>
 * 
 * @see BST.java
 * @author eder.magalhaes
 * @param <Key> parameterized type for key.
 * @param <Value> parameterized type for value.
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
 
    private Node root;
    
    //linked list
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int count = 1;

        public Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }
    
    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return (x.color == RED);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.count;
    }
    
    public int size() {
    	return size(root); 
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public Value get(Key key) { 
    	return get(root, key); 
    }

    private Value get(Node x, Key key) {
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

    public boolean contains(Key key) {
        return (get(key) != null);
    }
    
    public void put(Key k, Value v) {
        root = put(root, k, v);
        root.color = BLACK;
    }

    private Node put(Node h, Key k, Value v) { 
        if (h == null)
            return new Node(k, v, RED);

        int cmp = k.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left,  k, v); 
        else if (cmp > 0)
            h.right = put(h.right, k, v); 
        else
            h.val = v;

        //fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);
        
        h.count = size(h.left) + size(h.right) + 1;
        return h;
    }
    
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.count = h.count;
        h.count = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.count = h.count;
        h.count = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    
    public void delete(Key key) { 
        if (!contains(key)) {
            return;
        }

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        
        root = delete(root, key);
        if (!isEmpty()) 
            root.color = BLACK;
    }

    private Node delete(Node h, Key key) { 
        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                h.val = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }
    
    public void deleteMin() {
        if (isEmpty())
            return;
        
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        
        root = deleteMin(root);
        
        if (!isEmpty()) 
            root.color = BLACK;
    }

    private Node deleteMin(Node h) { 
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }
    
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) { 
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }
    
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) { 
            h = rotateRight(h);
        }
        return h;
    }
    
    private Node balance(Node h) {
        if (isRed(h.right))
            h = rotateLeft(h);
        
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        h.count = size(h.left) + size(h.right) + 1;
        return h;
    }
    
    public int height() {
    	return height(root);
    }
    
    private int height(Node x) {
        if (x == null)
            return 0;
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
        RedBlackBST<String, Integer> bst = new RedBlackBST<String, Integer>();
        
        bst.put("R", 1);
        bst.put("E", 1);
        bst.put("D", 1);
        bst.put("B", 1);
        bst.put("L", 1);
        bst.put("A", 1);
        bst.put("C", 1);
        bst.put("K", 1);
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
