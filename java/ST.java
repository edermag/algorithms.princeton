import static java.lang.System.out;

/**
 * Implements a <i>Symbol Table</i>, where keep key-value pair entries.
 * 
 * <h5>Lecture: Symbol Table API (Week 4)</h5>
 * 
 * <p>
 *  Insert a value with a specified key.
 *  Given a key, search for the corresponding value.
 * </p>
 * 
 * <p>Keys are ordered by rank (use <i>Binary Heap</i>).</p>
 * 
 * <p>
 *  This API define <i>Ordered</i> symbol table methods.
 *  More efficient than <code>STUnordered</code>.
 * </p>
 * 
 * 
 * @author eder.magalhaes
 *
 * @param <Key> parameterized type for key.
 * @param <Value> parameterized type for value.
 */
public class ST<Key extends Comparable<Key>, Value> {

    private static final int INIT_SIZE = 2;
    
    private int N;
    private Key[] keys;
    private Value[] vals;

    @SuppressWarnings("unchecked")
    public ST() {
    	keys = (Key[]) new Comparable[INIT_SIZE];
    	vals = (Value[]) new Object[INIT_SIZE];
    }
    
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
    	if (v == null) { 
    	    delete(k); 
    	    return; 
    	}
    	
    	int i = rank(k);
    	
    	//key is already in table
    	if (i < N && keys[i].compareTo(k) == 0) {
            vals[i] = v;
            return;
        }
    	
    	if (N >= keys.length) 
    	    resize(2*keys.length);
    	
    	//reset entries after i (rank)
    	for (int j = N; j > i; j--) {
    	    keys[j] = keys[j-1];
    	    vals[j] = vals[j-1];
    	}
    	
    	keys[i] = k;
    	vals[i] = v;
    	N++;
    }
    
    public Value get(Key k) {
    	if (isEmpty())
    	    return null;
    	
    	int i = rank(k);
        
    	if (i < N && keys[i].compareTo(k) == 0)
    	    return vals[i];
        
        return null;
    }
    
    //binary heap: return number of key
    private int rank(Key k) {
        int lo = 0, hi = N-1;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = k.compareTo(keys[mid]);
            
            if (cmp < 0)
                hi = mid - 1;
            else if (cmp > 0)
                lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }
    
    public void delete(Key k) {
        if (isEmpty())
            return;
        
        int i = rank(k);
        
        //key no found
        if (i == N || keys[i].compareTo(k) != 0)
            return;
        
        //reset entries after i (rank)
        for (int j = i; j < N-1; j++) {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }
        
        N--;
        keys[N] = null; //last null
        vals[N] = null;
    }
    
    public boolean contains(Key k) {
        return get(k) != null;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < N; i++)
            queue.enqueue(keys[i]);
        return queue;
    }
    
    public Key min() {
        if (isEmpty())
            return null;
        
        return keys[0];
    }
    
    public Key max() {
        if (isEmpty())
            return null;
        
        return keys[N-1];
    }
    
    public Key select(int i) {
    	if (i < 0 || i >= N) 
    	    return null;
    	
    	return keys[i];
    }
    
    public Key floor(Key k) {
    	int i = rank(k);
    	
    	if (i == 0)
    	    return null; //key not found
    	else
    	    return keys[i-1];
    }
    
    public Key ceiling(Key k) {
    	int i = rank(k);
    	
    	if (i == N)
    	    return null; //key not found
    	else
    	    return keys[i+1];
    }
    
    public static void main(String[] args) {
        ST<String, Integer> st = new ST<String, Integer>();
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
        
        out.printf("%nMax key: %s - Min key: %s%n", st.max(), st.min());
        out.printf("Ceiling of %s key: %s%n", st.min(), st.ceiling(st.min()));
        out.printf("Floor of %s key: %s", st.max(), st.floor(st.max()));
    }

}
