import static java.lang.System.out;

/**
 * Implements a <i>Separate chaining symbol table</i>.
 * 
 * <h5>Lecture: Linear Probing (Week 6)</h5>
 * 
 * <p>Another way to resolve collision <code>hashcode</code> in Symbol table.</p>
 * 
 * <p>In the worst case, this is a lg N * in running time.</p>
 * 
 * @author eder.magalhaes
 * @param <Key> parameterized type for key.
 * @param <Value> parameterized type for value.
 */
public class LinearProbingHashST<Key, Value> {

    private int M = 30001;
    
    private int N;
    private Value[] vals;
    private Key[] keys;
    
    @SuppressWarnings("unchecked")
    public LinearProbingHashST() {
        vals = (Value[]) new Object[M];
        keys = (Key[]) new Object[M];
    }
    
    private int hash(Key k) {
        return k.hashCode() & 0x7fffffff % M;
    }
    
    public void put(Key k, Value v) {
        if (v== null) delete(k);
        
        if (N >= M/2) resize(2*M);
        
        int i;
        for (i = hash(k); keys[i] != null; i = (i+1) % M)
            if (keys[i].equals(k))
                break;
        
        keys[i] = k;
        vals[i] = v;
        N++;
    }
    
    public Value get(Key k) {
        for (int i = hash(k); keys[i] != null; i = (i+1) % M)
            if (k.equals(keys[i]))
                return vals[i];
        
        return null;
    }
    
    public boolean contains(Key k) {
        return get(k) != null;
    }
    
    public void delete(Key k) {
        if (!contains(k)) 
            return;
        
        int i = hash(k);
        while (!k.equals(keys[i])) {
            i = (i + 1) % M;
        }
        
        keys[i] = null;
        vals[i] = null;
        
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % M;
        }
        N--;
        
        if (N > 0 && N <= M/8)
        	resize(M/2);
    }
    
    private void resize(int capacity) {
        Key[] copykeys = (Key[]) new Object[capacity];
        Value[] copyvals = (Value[]) new Object[capacity];
        
        for (int i=0; i < N; i++) {
            copykeys[i] = keys[i];
            copyvals[i] = vals[i];
        }
        keys = copykeys;
        vals = copyvals;
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

