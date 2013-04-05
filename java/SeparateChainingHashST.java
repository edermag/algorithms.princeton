import static java.lang.System.out;

/**
 * Implements a <i>Separate chaining symbol table</i>.
 * 
 * <h5>Lecture: Separate Chaining (Week 6)</h5>
 * 
 * <p>Alternative for <code>hashcode</code> collision. Use an array of M < N linked lists.</p>
 * 
 * <p>In the worst case, this is a lg N * in running time.</p>
 * 
 * @author eder.magalhaes
 * @param <Key> parameterized type for key.
 * @param <Value> parameterized type for value.
 */
public class SeparateChainingHashST<Key, Value> {

    private int M = 97;
    
    private Node[] st = new Node[M];
    private int N;
    
    private static class Node {
        private Object key;
        private Object val;
        private Node next;
        
        Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    
    //calculates the index
    private int hash(Key k) {
        return k.hashCode() & 0x7fffffff % M;
    }
    
    public Value get(Key k) {
        int i = hash(k);
        for (Node x = st[i]; x != null; x = x.next) {
            if (k.equals(x.key))
                return (Value) x.val;
        }
        return null;
    }
    
    public void put(Key k, Value v) {
        if (v == null) delete(k);
        
        if (N >= 10*M) resize(2*M);
        
        int i = hash(k);
        for (Node x = st[i]; x != null; x = x.next) {
            //replace
            if (k.equals(x.key)) { 
                x.val = v; 
                return; 
            }
        }
        st[i] = new Node(k, v, st[i]);
        N++;
    }
    
    private void resize(int capacity) {
        Node[] copy = new Node[capacity];
        
        for (int i=0; i < N; i++)
            copy[i] = st[i];
        
        st = copy;
    }
    
    public int size() {
        return N;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public boolean contains(Key k) {
        return get(k) != null;
    }
    
    public void delete(Key k) {
        if (!contains(k)) 
            return;
        
        int i = hash(k);
        Node o = null;
        for (Node x = st[i]; x != null; x = x.next) {
            if (k.equals(x.key)) { 
                if (o != null)
                    o.next = x.next;
                else
                    st[i] = x.next;
                N--;
            }
            o = x;
        }
        
        if (N > 0 && N <= M/8)
            resize(M/2);
    }
    
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                queue.enqueue((Key)x.key);
            }
        }
        return queue;
    }
    
    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
        st.put("S", 1);
        st.put("E", 1);
        st.put("P", 1);
        st.put("A", 1);
        st.put("R", 1);
        st.put("E", 1);
        st.put("T", 1);
        st.put("E", 1);
        st.put("C", 1);
        st.put("H", 1);
        st.put("A", 1);
        st.put("I", 1);
        st.put("N", 1);
        
        for (String k: st.keys())
            out.printf("%s (key) %s (Value) %n", k, st.get(k));
    }
}

