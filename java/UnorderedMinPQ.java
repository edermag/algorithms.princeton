import static java.lang.System.out;

import java.util.NoSuchElementException;

/**
 * Implements a <i>Priority Queue</i> unordered that iterates over the smallest key.
 * 
 * <h5>Lecture: APIs and Elementary Implementations (Week 4)</h5>
 * 
 * <p>
 *  Keep the entries unordered in an array, when method <code>delMin<code> 
 *  is called it remove and return the smallest key.
 * </p>
 * 
 * @see MinPQ.java
 * @author eder.magalhaes
 * @param <Key> parameterized type for key.
 */
public class UnorderedMinPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N;
    
    public UnorderedMinPQ() {
    	this(1);
    }
    
    @SuppressWarnings("unchecked")    
    public UnorderedMinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }
    	
    public boolean isEmpty() {
        return N == 0;
    }
    
    public void insert(Key k) {
    	if (N == pq.length - 1) 
    	    resize(2 * pq.length);
    	
        pq[N++] = k;
    }
    
    public Key delMin() {
    	if (isEmpty()) 
    	    throw new NoSuchElementException();
    	
        int min = 0;
        for (int i = 1; i < N; i++)
            if (less(i, min))
                min = i;
        
        exch(min, N-1);
        Key k = pq[--N];
        pq[N] = null;
        
        if ((N > 0) && (N == (pq.length - 1) / 4)) 
            resize(pq.length  / 2);
        
        return k;
    }
    
    private boolean less(int i, int j) {
    	return pq[i].compareTo(pq[j]) < 0;
    }
    
    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
    
    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
        Key[] copy = (Key[]) new Comparable[capacity];
        
        for (int i = 0; i <= N; i++) 
            copy[i] = pq[i];
        
        pq = copy;
    }
    
    public static void main(String[] args) {
    	UnorderedMinPQ<String> queue = new UnorderedMinPQ<String>();
        queue.insert("P");
        queue.insert("R");
        queue.insert("I");
        queue.insert("O");
        queue.insert("R");
        queue.insert("I");
        queue.insert("T");
        queue.insert("Y");
        queue.insert("Q");
        queue.insert("U");
        queue.insert("E");
        queue.insert("U");
        queue.insert("E");
        
        while (!queue.isEmpty()) {
            out.printf("%s ", queue.delMin());
        }
    }
}
