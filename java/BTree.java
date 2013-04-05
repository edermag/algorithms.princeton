import static java.lang.System.out;

/**
 * Implements a <i>B-Trees</i>.
 * 
 * <h5>Lecture: B-Trees (Week 5)</h5>
 * 
 * <p>Generalize 2-3 trees by allowing up to M - 1 key-link pairs per node.</p>
 * 
 * @author eder.magalhaes
 * @param <Key> parameterized type for key.
 * @param <Value> parameterized type for value.
 */
public class BTree<Key extends Comparable<Key>, Value> {

    private static final int M = 4;

    private Node root;
    //height of BTree
    private int HT;
    //number of key-value pairs in BTree
    private int N;
    
    //linked list
    public static final class Node {
        private int m; //number of children
        private Entry[] children = new Entry[M];
        
        Node(int k) {
            m = k;
        }
    }
    
    private static class Entry {
        private Comparable key;
        private Object value;
        
        private Node next;
        
        Entry(Comparable key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    
    public BTree() {
        root = new Node(0);
    }
    
    public int size() {
        return N;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int height() {
        return HT;
    }
    
    public Value get(Key k) {
        return search(root, k, HT);
    }
    
    private Value search(Node x, Key k, int ht) {
        Entry[] children = x.children;
        
        if (ht == 0) {
            for (int j=0; j < x.m; j++) {
                if (eq(k, children[j].key))
                    return (Value) children[j].value;
            }
        } else {
            for (int j = 0; j < x.m; j++) {
                if (j+1 == x.m || less(k, children[j+1].key))
                    return search(children[j].next, k, ht-1);
            }
        }
        return null;
    }
    
    public void put(Key k, Value v) {
        Node u = insert(root, k, v, HT);
        N++;
        
        if (u == null)
            return;
        
        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        
        root = t;
        HT++;
    }
    
    private Node insert(Node h, Key k, Value v, int ht) {
        int j;
        Entry t = new Entry(k, v, null);
        
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(k, h.children[j].key))
                    break;
            }
        } else {
            for (j = 0; j < h.m; j++) {
                if ((j+1 == h.m) || less(k, h.children[j+1].key)) {
                    Node u = insert(h.children[j++].next, k, v, ht-1);
                    
                    if (u == null)
                        return null;
                    
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }
        
        for (int i = h.m; i > j; i--)
            h.children[i] = h.children[i-1];
        
        h.children[j] = t;
        h.m++;
        if (h.m < M)
            return null;
        else
            return split(h);
    }
    
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        return keys(root, HT, queue);
    }
    
    private Iterable<Key> keys(Node h, int ht, Queue<Key> queue) {
        Entry[] children = h.children;
        
        if (ht == 0) {
            for (int j = 0; j < h.m; j++)
                queue.enqueue((Key)children[j].key);
        }
        else {
            for (int j = 0; j < h.m; j++)
                queue = (Queue<Key>) keys(children[j].next, ht-1, queue);
        }
        
        return queue;
    }
    
    private Node split(Node h) {
        Node t = new Node(M/2);
        h.m = M / 2;
        for (int j = 0; j < M / 2; j++)
            t.children[j] = h.children[M / 2 + j];
        return t;
    }
    
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }
    
    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }
    
    public static void main(String[] args) {
        BTree<String, Integer> btree = new BTree<String, Integer>();
        btree.put("S", 1);
        btree.put("E", 1);
        btree.put("P", 1);
        btree.put("A", 1);
        btree.put("R", 1);
        btree.put("E", 1);
        btree.put("T", 1);
        btree.put("E", 1);
        btree.put("C", 1);
        btree.put("H", 1);
        btree.put("A", 1);
        btree.put("I", 1);
        btree.put("N", 1);
        
        for (String k: btree.keys())
            out.printf("%s (key) %s (Value) %n", k, btree.get(k));
    }
}
