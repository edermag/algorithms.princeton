import static java.lang.System.out;

/**
 * This is another example of <i>Mergesort</i>.
 * 
 * <h5>Lecture: Mergesort (Week 3)</h5>
 * 
 * <p>The bases is the same of <code>Merge</code>, but with improvements.</p>
 * 
 * <p>For small subarrays use insertion sort. Mergesort has too much overhead for tiny subarrays.</p>
 * 
 * @see Merge.java
 * @author eder.magalhaes
 */
public class MergeImprove {

    private static final int CUTOFF = 7;
    
    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++ )
            if (less(a[i], a[i-1]))
                return false;
        
        return true;
    }
    
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }
    
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        //improvement: use insertionSort for small subarray
    	if (hi <= lo + CUTOFF) { 
            insertionSort(aux, lo, hi);
            return;
        }
        
        int mid = lo + (hi - lo) / 2; //split the array in 2 parts
        
        sort(a, aux, lo, mid); //left
        sort(a, aux, mid+1, hi); //right
        
        //improvement: array already order
        if (!less(a[mid+1], a[mid])) {
            System.arraycopy(a, lo, aux, lo, hi - lo + 1);
            return;
        }
        
        merge(a, aux, lo, mid, hi);
    }
    
    public static void sort(Comparable[] a) {
        //improvement: clone() is a bit faster than the above loop (old merge)
        Comparable[] aux = a.clone();
        
        //first call, full array
        sort(a, aux, 0, a.length - 1);
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[] {55, 18, 31, 79, 48, 88, 81, 38, 10, 90, 84, 44};
        sort(data);
        
        out.print("Array sorted by Mergesort (with improvements): ");
        for (Integer x: data) {
            out.printf("%s ",x);
        }
    }
    
}

