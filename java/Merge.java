import static java.lang.System.out;

/**
 * Implements a <i>Mergesort</i>.
 * 
 * <h5>Lecture: Mergesort (Week 3)</h5>
 * 
 * <p>This algorithms follow the steps (recursively):</p>
 * 
 * <ul>
 *   <li>Divide the array into two halves (left & right);</li>
 *   <li>Recursively sort each half;</li>
 *   <li>Merge two halves;</li>
 * </ul>
 * 
 * <p>Divide and conquer recurrence, this is logarithmic.</p>
 *
 * 
 * @see MergeImprove.java
 * @see MergeBU.java
 * @author eder.magalhaes
 */
public class Merge {

	private static Comparable[] aux;
	
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
        //copy the array
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        
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
    	if (hi <= lo)
            return;
        
        int mid = lo + (hi - lo) / 2; //split the array in 2 parts
        
        sort(a, aux, lo, mid); //left
        sort(a, aux, mid+1, hi); //right
        
        merge(a, aux, lo, mid, hi);
    }
    
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        
        //first call, full array
        sort(a, aux, 0, a.length - 1);
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[] {55, 18, 31, 79, 48, 88, 81, 38, 10, 90, 84, 44};
        sort(data);
        
        out.print("Array sorted by Mergesort: ");
        for (Integer x: data) {
            out.printf("%s ",x);
        }
    }
    
}
