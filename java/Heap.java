import static java.lang.System.out;

/**
 * Implements a <i>Heapsort</i>.
 * 
 * <h5>Lecture: Heapsort (Week 4)</h5>
 * 
 * <p>
 *  Worst case this algorithms works in 2 N lg N running time.
 *  Best case works in N lg N running time. 
 * </p>
 * 
 * <p>
 *  Faster than Selection, Insertion, Shell, Quick and 3-way quick.
 *  But slow than Mergesort.
 * </p>
 * 
 * @author eder.magalhaes
 */
public class Heap {

    private static void sink (Comparable[] pq, int k, int N) {
    	while (2 * k <= N) {
    	    int j = 2 * k;
    	    if (j < N && less(pq, j, j+1))
    	        j++;
    		
    	    if (!less(pq, k, j))
    	        break;
    		
    	    exch(pq, k, j);
    	    k = j;
    	}
    }
    
    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }
    
    public static void sort(Comparable[] pq) {
    	int N = pq.length;
    	
    	for (int k = N/2; k >= 1; k--) 
    	    sink(pq, k, N);
    	
    	while (N > 1) {
    	    exch(pq, 1, N--);
    	    sink(pq, 1, N);
    	}
    }
    
    public static void main(String[] args) {
    	Integer[] data = new Integer[] {95, 29, 57, 82, 41, 83, 52, 21, 94, 79};
        sort(data);
        
        out.print("Array sorted by Heapsort: ");
        for (Integer x: data) {
            out.printf("%s ",x);
        }
    }
}

