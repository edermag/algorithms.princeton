import static java.lang.System.out;

/**
 * Implements a unordered sequencial serach <i>Symbol Table</i>, where keep key-value pair entries.
 * 
 * <h5>Lecture: Symbol Table API (Week 4)</h5>
 * 
 * <p>
 *  Insert a value with a specified key.
 *  Given a key, search for the corresponding value.
 * </p>
 * 
 * <p>Keep the keys in sequence (put order), using linked list.</p>
 * 
 * @see ST.java
 * @author eder.magalhaes
 *
 * @param <Key> parameterized type for key.
 * @param <Value> parameterized type for value.
 */
public class STSequencial<Key extends Comparable<Key>, Value> {

    private Node first;
    private int N;
    
    //linked list
    private class Node {
        private Key key;
        private Value val;
        private Node next;
        
        public Node(Key key, Value val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }
    
    public void put(Key k, Value v) {
        if (v == null) {
            delete(k);
            return;
        }
        
        Node x = first;
        while (x != null) {
            if (k.equals(x.key)) {
                x.val = v;
                return;
            }
            x = x.next;
        }
        
        first = new Node(k, v, first);
        N++;
    }
    
    public Value get(Key k) {
        Node x = first;
        while (x != null) {
            if (x.key.equals(k))
                return x.val;
            
            x = x.next;
        }
        return null;
    }
    
    public void delete(Key k) {
        first = delete(first, k);
    }
    
    private Node delete(Node x, Key k) {
        if (x == null)
            return null;
        
        if (k.equals(x.key)) {
            N--;
            return x.next;
        }
        //check next (recursively)
        x.next = delete(x.next, k);
        return x;
    }
    public boolean contains(Key k) {
        return get(k) != null;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public Iterable<Key> keys()  {
    	Queue<Key> queue = new Queue<Key>();
    	Node x = first;
        while (x != null) {
            queue.enqueue(x.key);
            x = x.next;
        }
        return queue;
    }
    
    public Iterable<Value> values()  {
    	Queue<Value> queue = new Queue<Value>();
    	Node x = first;
        while (x != null) {
            queue.enqueue(x.val);
            x = x.next;
        }
        return queue;
    }
    
    public static void main(String[] args) {
    	STSequencial<String, Integer> st = new STSequencial<String, Integer>();
        st.put("S", 52);
        st.put("I", 23);
        st.put("M", 6);
        st.put("B", 17);
        st.put("O", 31);
        st.put("L", -20);
        st.put("T", 33);
        st.put("A", 77);
        st.put("B", 6); //repeat key B
        st.put("L", 7); //repeat key L
        st.put("E", 8);
        
        for (String k: st.keys())
            out.printf("%s (key) %s (Value) %n", k, st.get(k));
    }
}
