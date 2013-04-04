import static java.lang.System.out;

/**
 * Implements a unordered <i>Symbol Table</i>, where keep key-value pair entries.
 * 
 * <h5>Lecture: Symbol Table API (Week 4)</h5>
 * 
 * <p>
 *  Insert a value with a specified key.
 *  Given a key, search for the corresponding value.
 * </p>
 * 
 * <p>Keep the keys in sequence (put order).</p>
 * 
 * @see ST.java
 * @author eder.magalhaes
 *
 * @param <Key> parameterized type for key.
 * @param <Value> parameterized type for value.
 */
public class STUnordered<Key extends Comparable<Key>, Value> {

    private static final int INIT_SIZE = 10;

    private Key[] keys;
    private Value[] vals; 
    private int N;

    @SuppressWarnings("unchecked")
    public STUnordered() {
    	keys = (Key[]) new Comparable[INIT_SIZE];
    	vals = (Value[]) new Object[INIT_SIZE];
    }
    
    //resize two arrays
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        Key[] copykeys = (Key[]) new Comparable[capacity];
        Value[] copyvals = (Value[]) new Object[capacity];
        
        for (int i = 0; i < N; i++) {
            copykeys[i] = keys[i];
            copyvals[i] = vals[i];
        }
        
        keys = copykeys;
        vals = copyvals;
    }
    
    public void put(Key k, Value v) {
    	delete(k);//no duplicates
    	
    	if (N >= vals.length) 
    	    resize(2*N);
    	
    	vals[N] = v;
    	keys[N] = k;
    	N++;
    }
    
    public Value get(Key k) {
        for (int i = 0; i < N; i++)
            if (keys[i].equals(k))
                return vals[i];
        
        return null;
    }
    
    public void delete(Key k) {
        for (int i = 0; i < N; i++) {
            if (keys[i].equals(k)) {
                keys[i] = keys[N-1]; //replace with last
                vals[i] = vals[N-1];
                keys[N-1] = null;
                vals[N-1] = null;
                N--;
                return;
            }
        }
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
    
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < N; i++)
            queue.enqueue(keys[i]);
        return queue;
    }
    
    public Iterable<Value> values() {
        Queue<Value> queue = new Queue<Value>();
        for (int i = 0; i < N; i++) 
            queue.enqueue(vals[i]);
        return queue;
    }
    
    public static void main(String[] args) {
        STUnordered<String, Integer> st = new STUnordered<String, Integer>();
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
